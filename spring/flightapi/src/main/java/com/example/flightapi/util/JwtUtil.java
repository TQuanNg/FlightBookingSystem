package com.example.flightapi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;


public class JwtUtil {
    private static final Key SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours


    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    
    public static String extractUsername(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(SIGNING_KEY).build();

        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        return claims.getSubject();
    }
}

