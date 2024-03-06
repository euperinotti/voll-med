package med.voll.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.application.service.TokenService;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private TokenService tokenService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
        String token = this.getToken(request);

        String subject = tokenService.decryptToken(token);

    filterChain.doFilter(request, response);
  }

  private String getToken(HttpServletRequest request) {
    String header = request.getHeader("Authorization");

    if (header == null) throw new RuntimeException("Invalid authorization header");

    return header.replace("Bearer ", "");
  }

}
