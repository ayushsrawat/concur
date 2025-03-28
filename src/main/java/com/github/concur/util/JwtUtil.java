package com.github.concur.util;

import com.github.concur.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

  @Value("${jwt.secret.key}")
  private String JWT_SECRET_KEY;

  public String generateToken(User user) {
    return Jwts.builder()
      .subject(user.getUsername())
      .claim("role", user.getUserRole().getName())
      .issuedAt(new Date())
      .expiration(new Date(System.currentTimeMillis() + 86400000)) //24hrs
      .signWith(Jwts.SIG.HS256.key().build())
      .compact();
  }

  public String extractUserRole(String token) {
    return Jwts.parser()
      .verifyWith(Jwts.SIG.HS256.key().build())
      .build()
      .parseSignedClaims(token)
      .getPayload()
      .get("role", String.class);
  }

  private Key getSigningKey() {
    byte[] keyBytes = JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }

}
