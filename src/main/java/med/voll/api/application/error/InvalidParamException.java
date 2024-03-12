package med.voll.api.application.error;

public class InvalidParamException extends Throwable {
  public InvalidParamException(String message) {
    super(message);
  }
}
