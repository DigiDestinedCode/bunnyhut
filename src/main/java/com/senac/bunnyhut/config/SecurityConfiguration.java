package com.senac.bunnyhut.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Autowired
    private UserAuthenticationFilter userAuthenticationFilter;

    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/api/user/login",
            "/api/user/create",
            "/h2-console",
            // Swagger/OpenAPI UI
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    // Endpoints that require authentication
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            //Background_Slot
            "/api/backgroundslot/list",
            "/api/backgroundslot/listById/**",

            //Background
            "/api/background/list",
            "/api/background/listById/**",

            //Furniture
            "/api/furniture/list",
            "/api/furniture/listById/**",

            //Garden_Spot
            "/api/gardenspot/list",
            "/api/gardenspot/listById/**",

            //Garden
            "/api/garden/list",
            "/api/garden/listById/**",

            //Inventory
            "/api/inventory/list",
            "/api/inventory/listById/**",

            //Item
            "/api/item/list",
            "/api/item/listById/**",

            //Plant
            "/api/plant/list",
            "/api/plant/listById/**",

            //Rabbit
            "/api/rabbit/list",
            "/api/rabbit/listById/**",

            //Transaction_Coin
            "/api/transactioncoin/list",
            "/api/transactioncoin/listById/**",

            //User
            "/api/user/list",
            "/api/user/listById/**",

            //Visit
            "/api/visit/list",
            "/api/visit/listById/**",
    };

    // Endpoints for regular users (if needed in the future)
    public static final String [] ENDPOINTS_USER = {
    };

    // Endpoints for administrators
    public static final String [] ENDPOINTS_ADMIN = {

            //Background_Slot
            "/api/backgroundslot/create",
            "/api/backgroundslot/update/**",

            //Background
            "/api/background/create",
            "/api/background/update/**",

            //Furniture
            "/api/furniture/create",
            "/api/furniture/update/**",

            //Garden_Spot
            "/api/gardenspot/create",
            "/api/gardenspot/update/**",

            //Garden
            "/api/garden/create",
            "/api/garden/update/**",

            //Inventory
            "/api/inventory/create",
            "/api/inventory/update/**",

            //Item
            "/api/item/create",
            "/api/item/update/**",
            "/api/item/delete/**",

            //Plant
            "/api/plant/create",
            "/api/plant/update/**",

            //Rabbit
            "/api/rabbit/create",
            "/api/rabbit/update/**",

            //Transaction_Coin
            "/api/transactioncoin/create",
            "/api/transactioncoin/update/**",

            //User
            "/api/user/update/**",
            "/api/user/delete/**",

            //Visit
            "/api/visit/create",
            "/api/visit/update/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMIN")
                        .requestMatchers(ENDPOINTS_USER).hasRole("USER")
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()
                        .anyRequest().denyAll()
                )
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}