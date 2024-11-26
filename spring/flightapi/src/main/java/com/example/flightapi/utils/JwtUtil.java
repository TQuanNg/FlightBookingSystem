package com.example.flightapi.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

public class JwtUtil {
    private static final String SECRET_KEY = "your_secret_key"; // Use a secure key
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    private static final Key SIGNING_KEY = new SecretKeySpec(
            SECRET_KEY.getBytes(),
            SignatureAlgorithm.HS256.getJcaName()
    );

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}

