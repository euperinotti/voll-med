package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.application.dto.DadosAgendamentoConsulta;
import med.voll.api.application.dto.DadosCancelamentoConsulta;
import med.voll.api.application.dto.DadosDetalhamentoConsulta;
import med.voll.api.application.error.InvalidParamException;
import med.voll.api.application.service.ConsultaService;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

  @Autowired
  private ConsultaService service;

  @PostMapping
  @Transactional
  public ResponseEntity<DadosDetalhamentoConsulta> agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta body) throws InvalidParamException {
    DadosDetalhamentoConsulta dto = service.agendar(body);
    return ResponseEntity.ok(dto);
  }

  @DeleteMapping
  @Transactional
  public ResponseEntity<Object> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) throws InvalidParamException {
    service.cancelar(dados);
    return ResponseEntity.noContent().build();
  }
}
