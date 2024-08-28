package com.balasegaran.AbcRestuarant.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

  private static final String SECRET_KEY = "your_secret_key";

  public static String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours validity
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }

  public static Claims extractClaims(String token) {
    return Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody();
  }

  public static boolean validateToken(String token, String username) {
    final String tokenUsername = extractClaims(token).getSubject();
    return (username.equals(tokenUsername) && !isTokenExpired(token));
  }

  private static boolean isTokenExpired(String token) {
    return extractClaims(token).getExpiration().before(new Date());
  }
}
