package med.voll.api.infra.dto;

import org.springframework.validation.FieldError;

public record ValidationErrorDTO(String field, String message) {

  public ValidationErrorDTO(FieldError fieldError) {
    this(fieldError.getField(), fieldError.getDefaultMessage());
  }
  
}
