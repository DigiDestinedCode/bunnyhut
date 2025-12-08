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
    private JwtTokenService jwtTokenService; // Service que definimos anteriormente

    @Autowired
    private UserRepository userRepository; // Repository que definimos anteriormente

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Verifica se o endpoint requer autenticação antes de processar a requisição
        try {
            if (checkIfEndpointIsNotPublic(request)) {
                String token = recoveryToken(request); // Recupera o token do cabeçalho Authorization da requisição

                if (token != null) {
                    String email = jwtTokenService.getSubjectFromToken(token); // Obtém o assunto (neste caso, o nome de usuário) do token
                    Optional<User> userOptional = userRepository.findByEmail(email); // Busca o usuário pelo email (que é o assunto do token)

                    if (userOptional.isPresent() && userOptional.get().getStatus() >= 0) {
                        User user = userOptional.get();
                        UserDetailsImpl userDetails = new UserDetailsImpl(user); // Cria um UserDetails com o usuário encontrado

                        // Cria um objeto de autenticação do Spring Security
                        Authentication authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails.getUsername(), null,
                                        userDetails.getAuthorities()
                                );

                        // Define o objeto de autenticação no contexto de segurança do Spring Security
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else  {
                        response.setStatus(401);
                        response.setContentType("application/json");
                        response.getWriter().write("{\"error\": \"Usuário não encontrado ou inativo\"}");
                        return;
                    }
                } else {
                    response.setStatus(401);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\": \"Token ausente\"}");
                    return;
                }
            }
            filterChain.doFilter(request, response); // Continua o processamento da requisição
        } catch (Exception e) {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token invalido ou expirado\"}");
        }
    }

    // Recupera o token do cabeçalho Authorization da requisição
    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    // Verifica se o endpoint requer autenticação antes de processar a requisição
    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return Arrays.stream(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED)
                .noneMatch(publicEndpoint -> {
                    String cleanEndpoint = publicEndpoint.replace("/**", "");
                    return requestURI.startsWith(cleanEndpoint);
                });
    }

}