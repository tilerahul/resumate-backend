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
    public static String SECRET_KEY="mynameisrahultilemynameisrahultile";



    private static long EXPIRATION_TIME = 60 * 60 * 1000; // 1 hour

    private static Key getSigningKey() {
        // Keys.hmacShaKeyFor requires key length >= 32 chars for HS256
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public static String generateToken(String userId, Boolean isAdmin) {
//        System.out.print("userID : "+userId);
//        System.out.print("admin : "+isAdmin);

        return Jwts.builder()
                .claim("userId", userId)
                .claim("isAdmin", isAdmin)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
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
