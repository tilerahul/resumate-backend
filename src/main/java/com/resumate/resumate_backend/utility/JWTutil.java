package com.resumate.resumate_backend.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JWTutil {

    //@Value("${secret-key}")
    public static String SECRET_KEY="mynameisrahultile";



    private final long EXPIRATION_TIME = 60 * 60 * 1000; // 1 hour

    private Key getSigningKey() {
        // Keys.hmacShaKeyFor requires key length >= 32 chars for HS256
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String userId, Boolean isAdmin) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("isAdmin", isAdmin)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

//    public Claims extractClaims(String token) {
//        return Jwts.parser()
//                .verifyWith(getSigningKey())   // new way in 0.13.0
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
}
