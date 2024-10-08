package com.redy.blogbackend.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private String SECRET_KEY = "ZbdZiv10fD1exI52/euqvTYokaNNULDIE9/AFQflYsqnOeAT3iRYXqgQnOOLEBDdsDnxrRQd+bunGUMnMBrtLewYd23aLD0YoA36VzWz9OazL5IyeeqxtF0Grjfl7POkcBKBZ3k4GMK16F9tvb/Sd1vlouOxC7lKBTfpnFTAMG3ph+BuePors/UVqm4SyrmExRz9o7cOtnDlP1tw09skUjp+Aa3BvSn6JdgGqGt7UQcHEvrGweG/wSJUrcwkUiOkwl6OQQfX4zVoYBeXXBppFsQHVm8yPa2530M8i8Bh/badul4kukf6iJeRMFX+/iwI2nmdvVe4TZy6HZ7BRSGBgw==";


    private String API_PATH = "/api";

    public ResponseCookie generateTokenCookie(String username) {
        long maxAgeSeconds = 3600 * 24;
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * maxAgeSeconds))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
        return ResponseCookie.from("accessToken", token)
                .httpOnly(true)
                .secure(false)
                .sameSite("strict")
                .path(API_PATH)
                .maxAge(maxAgeSeconds)
                .build();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
