package com.dust.monitoring.api.analytics.utils;

import com.dust.monitoring.api.analytics.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtUtils {

    private Key key;

    @Value("${jwt.secret}")
    private String secret;

    @Getter
    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs;

    @Getter
    @Value("${jwt.refresh.expirationMs}")
    private long jwtRefreshExpirationMs;

    @PostConstruct
    public void init() {
        // Convert secret to 256-bit key
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // Create a token for the given user
    public String createToken(Users users, Boolean isAccessToken) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", users.getEmail());
        claims.put("roles", users.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(users.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()
                        + (isAccessToken ? jwtExpirationMs : jwtRefreshExpirationMs)))
                .signWith(key)
                .compact();
    }

    // Validate the token
    public boolean validateToken(String token) {
        try {
            if (isTokenExpired(token)) {
                return false;
            }
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Extract username from the token
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    // Extract claims from the token
    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}





