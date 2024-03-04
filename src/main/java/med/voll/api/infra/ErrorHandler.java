package med.voll.api.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handle404Error() {
    return ResponseEntity.notFound().build();
  }
}
