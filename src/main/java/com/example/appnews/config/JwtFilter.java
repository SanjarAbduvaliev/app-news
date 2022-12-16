package com.example.appnews.config;

import com.example.appnews.serivice.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter  extends OncePerRequestFilter {

    private  final JwtProvider jwtProvider;

    private final AuthService authService;
    @Autowired
    JwtFilter (JwtProvider jwtProvider, AuthService authService){
        this.jwtProvider=jwtProvider;
        this.authService=authService;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization!=null&&authorization.startsWith("Bearer")){
            String token = authorization.substring(7);
            String username = jwtProvider.parserToken(token);
            if (!username.isEmpty()){
                UserDetails cardUserDetails = authService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
                        cardUserDetails,
                        null,
                        cardUserDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
        filterChain.doFilter(request,response);
    }
}
