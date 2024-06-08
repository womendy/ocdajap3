package com.oc.rental.configuration;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
@Value("jwt.secret")
  private String secret ;

  public String generateToken(String username) {
    return Jwts.builder()
      .setSubject(username)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token validity: 10 hours
      .signWith(SignatureAlgorithm.HS256, secret)
      .compact();
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  public String getUsernameFromJWT(String token) {
    Claims claims = Jwts.parser()
      .setSigningKey(secret)
      .parseClaimsJws(token)
      .getBody();
    return claims.getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      return true;
    }
    catch (Exception ex) {
      throw new AuthenticationCredentialsNotFoundException("JWT token is not valid " + token);
    }
  }

  public String getUserTypeFromJWT(String token) {
    Claims claims = Jwts.parser()
      .setSigningKey(secret)
      .parseClaimsJws(token)
      .getBody();
    return claims.get("usertype").toString();
  }
}
