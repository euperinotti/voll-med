package med.voll.api.infra;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.infra.errors.DadosErroValidacao;

@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handle404Error() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handle400Error(MethodArgumentNotValidException exception) {

    List<FieldError> errorsList = exception.getFieldErrors();

    return ResponseEntity.badRequest().body(errorsList.stream().map(DadosErroValidacao::new).toList());
  }
}
