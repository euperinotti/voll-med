package med.voll.api.application.error;

public class InvalidParamException extends RuntimeException {
  public InvalidParamException(String message) {
    super(message);
  }
}
