package med.voll.api.infra.dto;

import org.springframework.validation.FieldError;

public record DadosErroValidacao(String campo, String mensagem) {

  public DadosErroValidacao(FieldError fieldError) {
    this(fieldError.getField(), fieldError.getDefaultMessage());
  }
  
}
