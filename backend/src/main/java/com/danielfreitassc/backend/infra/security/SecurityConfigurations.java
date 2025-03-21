package com.danielfreitassc.backend.infra.security;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurations {
    private final SecurityFilter securityFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                
                .requestMatchers(HttpMethod.POST,"/users").permitAll()
                .requestMatchers(HttpMethod.GET,"/users").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.GET,"/users/{id}").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.PATCH,"/users/{id}").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.DELETE,"/users/{id}").hasAnyRole("ADMIN","USER")
                
                .requestMatchers(HttpMethod.POST,"/quizzes").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.GET,"/quizzes").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.GET,"/quizzes/{id}").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.PUT,"/quizzes/{id}").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.DELETE,"/quizzes/{id}").hasAnyRole("ADMIN","USER")
                         
                .requestMatchers(HttpMethod.POST,"/questions").permitAll()
                .requestMatchers(HttpMethod.GET,"/questions").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.GET,"/questions/{id}").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.PUT,"/questions/{id}").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.DELETE,"/questions/{id}").hasAnyRole("ADMIN","USER")

                .requestMatchers(HttpMethod.POST,"/alternatives").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.GET,"/alternatives").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.GET,"/alternatives/{id}").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.PUT,"/alternatives/{id}").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.DELETE,"/alternatives/{id}").hasAnyRole("ADMIN","USER")

                .requestMatchers(HttpMethod.POST,"/full-quizzes").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.GET,"/full-quizzes/{id}").hasAnyRole("ADMIN","USER")

                .requestMatchers(HttpMethod.POST,"/answers").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.GET,"/answers").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.GET,"/answers/{id}").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.PUT,"/answers/{id}").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.DELETE,"/answers/{id}").hasAnyRole("ADMIN","USER")

                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.GET,"/validation").permitAll()
                
                // Configuração para endpoint de erro
                .requestMatchers("/error").anonymous()
                .anyRequest().denyAll()) .exceptionHandling(exception -> 
                exception.authenticationEntryPoint(customAuthenticationEntryPoint)
            
                ).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedMethod(HttpMethod.POST);
        configuration.addAllowedMethod(HttpMethod.GET);
        configuration.addAllowedMethod(HttpMethod.PUT);
        configuration.addAllowedMethod(HttpMethod.PATCH);
        configuration.addAllowedMethod(HttpMethod.DELETE);
        configuration.addAllowedHeader("*"); 

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
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