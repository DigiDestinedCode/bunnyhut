package com.senac.bunnyhut.config;

import com.senac.bunnyhut.entity.User;
import com.senac.bunnyhut.repository.UserRepository;
import com.senac.bunnyhut.service.JwtTokenService;
import com.senac.bunnyhut.service.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Skip all logic for OPTIONS pre-flight requests
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Only check for a token if the endpoint is NOT public
        try {
            if (checkIfEndpointIsNotPublic(request)) {
                String token = recoveryToken(request); // Retrieve the token from the request Authorization header

                if (token != null) {
                    // --- DEBUG POINT START ---
                    System.out.println("DEBUG: Attempting token validation for URI: " + request.getRequestURI());
                    // --- DEBUG POINT END ---

                    String email = jwtTokenService.getSubjectFromToken(token); // Get the subject (username/email) from the token
                    Optional<User> userOptional = userRepository.findByEmail(email); // Find the user by email

                    if (userOptional.isPresent() && userOptional.get().getStatus() >= 0) {
                        User user = userOptional.get();
                        UserDetailsImpl userDetails = new UserDetailsImpl(user); // Create UserDetails

                        // Create Spring Security authentication object
                        Authentication authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails.getUsername(), null,
                                        userDetails.getAuthorities()
                                );

                        // Set the authentication object in the Spring Security context
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else  {
                        response.setStatus(401);
                        response.setContentType("application/json");
                        response.getWriter().write("{\"error\": \"User not found or inactive\"}");
                        return;
                    }
                } else {
                    response.setStatus(401);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\": \"Token missing\"}");
                    return;
                }
            }
            filterChain.doFilter(request, response); // Continue the request processing
        } catch (Exception e) {
            // This catches exceptions thrown by the JwtTokenService when the token is invalid/expired
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Invalid or expired token\"}");
        }
    }

    // Retrieves the token from the Authorization header
    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        // --- CRITICAL DEBUG POINT START ---
        // This will print the raw header value to the console for every request.
        System.out.println("DEBUG: RAW Authorization Header: " + authorizationHeader);
        // --- CRITICAL DEBUG POINT END ---

        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    // Checks if the endpoint requires authentication (returns TRUE if it requires a token)
    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI().toLowerCase();

        // Normalize the Request URI (remove trailing slash and lowercase)
        String tempURI = requestURI;
        if (tempURI.endsWith("/") && tempURI.length() > 1) {
            tempURI = tempURI.substring(0, tempURI.length() - 1);
        }
        final String finalNormalizedURI = tempURI;

        // Check if ANY public endpoint is a match.
        boolean isPublic = Arrays.stream(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED)
                .anyMatch(publicEndpoint -> {
                    String normalizedPublicEndpoint = publicEndpoint.toLowerCase().replace("/**", "");

                    // 1. Exact match (e.g., /api/user/create matches /api/user/create)
                    if (normalizedPublicEndpoint.equals(finalNormalizedURI)) {
                        return true;
                    }

                    // 2. Wildcard match (e.g., /v3/api-docs/** matches /v3/api-docs/something)
                    if (publicEndpoint.endsWith("/**")) {
                        if (finalNormalizedURI.startsWith(normalizedPublicEndpoint)) {
                            return true;
                        }
                    }
                    return false;
                });

        // The method returns TRUE if the endpoint is NOT public (i.e., requires a token)
        return !isPublic;
    }

}