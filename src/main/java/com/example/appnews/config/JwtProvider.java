package com.example.appnews.config;

import com.example.appnews.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtProvider {
    @Value("${app.jwt.secret}")
    private String secretKey;
    public String gerateToken(String username, Role role){

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role",role);

        String token = Jwts
                .builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 ))
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;
    }

public String parserToken (String token){
    try {
        String subject = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return subject;
    }
    catch (Exception e){
        return null;
    }
}
}
