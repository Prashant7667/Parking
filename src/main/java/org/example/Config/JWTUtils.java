package org.example.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.KeyStore;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtils {
    private final Key jwtSecret=Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long jwtExpirationsMs=1000*60*60*24*30;
    public String generateToken(String email,String role){
        return Jwts.builder()
                .setSubject(email)
                .addClaims(Map.of("Role",role))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpirationsMs))
                .signWith(jwtSecret)
                .compact();
    }
    private Claims parseClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private String validateTokenAndGetEmail(String token){
        try{
            Claims claims=parseClaims(token);
            return claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
