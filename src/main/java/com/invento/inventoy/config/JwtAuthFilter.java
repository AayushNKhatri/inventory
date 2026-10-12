package com.invento.inventoy.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtServices jwtServices;
    private final UserDetailsService userDetailsService;
    private static final String BEARER = "Bearer ";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getJWT(request);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            Claims claims = jwtServices.getValidClaims(token);
            if(claims != null){
                UserDetails details = userDetailsService.loadUserByUsername(claims.getSubject());
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
    private String getJWT(HttpServletRequest req)
    {
        String authHeader = req.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith(BEARER)){
            return authHeader.substring(BEARER.length());
        }
        return null;
    }

}
