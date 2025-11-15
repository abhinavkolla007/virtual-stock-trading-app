package com.nikolagrujic.tradingsimulator.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isJwtExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public String generateJwt(String email) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(getSigningKey())
                .compact();
    }

    public boolean isValidJwt(String token, org.springframework.security.core.userdetails.UserDetails userDetails) {
        return userDetails.getUsername().equals(extractEmail(token))
                && !isJwtExpired(token);
    }
}
