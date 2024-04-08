package med.voll.api.application.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import med.voll.api.domain.entities.User;

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(this.secret);
      return JWT.create()
          .withIssuer("voll-med")
          .withSubject(user.getLogin())
          .withExpiresAt(expirationTime())
          .sign(algorithm);
    } catch (JWTCreationException e) {
      throw new RuntimeException("Error generating token", e);
    }
  }

  private Instant expirationTime() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }

  public String decryptToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(this.secret);
      return JWT.require(algorithm)
          .withIssuer("voll-med")
          .build()
          .verify(token)
          .getSubject();
    } catch (JWTCreationException e) {
      throw new RuntimeException("Expired Token", e);
    }
  }

}
