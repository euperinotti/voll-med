package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.application.dto.AuthenticationDTO;
import med.voll.api.application.service.TokenService;
import med.voll.api.domain.entities.User;
import med.voll.api.infra.dto.JWTTokenDTO;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private TokenService tokenService;
  
  @PostMapping
  public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO request) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.login(), request.password());

    Authentication authentication = authManager.authenticate(authenticationToken);

    String tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

    return ResponseEntity.ok(new JWTTokenDTO(tokenJWT));

  }
}
