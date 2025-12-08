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
            "/api/user/login", // Url que usaremos para fazer login
            "/api/user/criar", //, Url que usaremos para criar um usuário
            "/h2-console",
            // Swagger/OpenAPI UI
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    // Endpoints que requerem autenticação para serem acessados
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {

            //Background_Slot
            "/api/background-slot/listar",
            "/api/background-slot/listarPorBackground_SlotId/**",

            //Background
            "/api/background/listar",
            "/api/background/listarPorBackgroundId/**",

            //Furniture
            "/api/furniture/listar",
            "/api/furniture/listarPorFurnitureId/**",

            //Garden_Spot
            "/api/garden-spot/listar",
            "/api/garden-spot/listarPorGarden_SpotId/**",

            //Garden
            "/api/garden/listar",
            "/api/garden/listarPorGardenId/**",

            //Inventory
            "/api/inventory/listar",
            "/api/inventory/listarPorInventoryId/**",

            //Item
            "/api/item/listar",
            "/api/item/listarPorItemId/**",

            //Plant
            "/api/plant/listar",
            "/api/plant/listarPorPlantId/**",

            //Rabbit
            "/api/rabbit/listar",
            "/api/rabbit/listarPorRabbitId/**",

            //Transaction_Coin
            "/api/transaction-coin/listar",
            "/api/transaction-coin/listarPorTransaction_CoinId/**",

            //User
            "/api/user/listar",
            "/api/user/listarPorUserId/**",

            //Visit
            "/api/visit/listar",
            "/api/visit/listarPorVisitId/**",
    };

    // Endpoints que só podem ser acessador por usuários com permissão de cliente
    public static final String [] ENDPOINTS_USER = {
    };

    // Endpoints que só podem ser acessador por usuários com permissão de administrador
    public static final String [] ENDPOINTS_ADMIN = {

            //Background_Slot
            "/api/background-slot/criar",
            "/api/background-slot/atualizar/**",

            //Background
            "/api/background/criar",
            "/api/background/atualizar/**",

            //Furniture
            "/api/furniture/criar",
            "/api/furniture/atualizar/**",

            //Garden_Spot
            "/api/garden-spot/criar",
            "/api/garden-spot/atualizar/**",

            //Garden
            "/api/garden/criar",
            "/api/garden/atualizar/**",

            //Inventory
            "/api/inventory/criar",
            "/api/inventory/atualizar/**",

            //Item
            "/api/item/criar",
            "/api/item/atualizar/**",
            "/api/item/apagar",

            //Plant
            "/api/plant/criar",
            "/api/plant/atualizar/**",

            //Rabbit
            "/api/rabbit/criar",
            "/api/rabbit/atualizar/**",

            //Transaction_Coin
            "/api/transaction-coin/criar",
            "/api/transaction-coin/atualizar/**",

            //User
            "/api/user/criar",
            "/api/user/atualizar/**",
            "/api/user/apagar",

            //Visit
            "/api/visit/criar",
            "/api/visit/atualizar/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // adicionando para visualizar em swagger
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
