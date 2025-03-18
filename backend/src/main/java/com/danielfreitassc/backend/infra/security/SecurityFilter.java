package com.danielfreitassc.backend.infra.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.danielfreitassc.backend.repositories.user.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String path = request.getServletPath();
            boolean isPublicEndpoint = (path.equals("/users") && request.getMethod().equals("POST")) 
                                    || path.equals("/validation");

            var token = this.recoverToken(request);

            // Se o token estiver presente, valide-o
            if (token != null) {
                var username = tokenService.validateToken(token);
                UserDetails user = userRepository.findByUsername(username);

                if (user != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new RuntimeException("Token inválido");
                }
            }

            // Se o endpoint for público e não houver token, permita a requisição
            if (isPublicEndpoint && token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            customAuthenticationEntryPoint.commence(request, response, null);
        }
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
