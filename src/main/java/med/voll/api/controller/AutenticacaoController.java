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
import med.voll.api.application.dto.DadosAutenticacao;
import med.voll.api.application.service.TokenService;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.dto.DadosTokenJWT;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private TokenService tokenService;
  
  @PostMapping
  public ResponseEntity<Object> login(@RequestBody @Valid DadosAutenticacao request) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.login(), request.senha());

    Authentication authentication = authManager.authenticate(authenticationToken);

    String tokenJWT = tokenService.generateToken((Usuario) authentication.getPrincipal());

    return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));

  }
}
