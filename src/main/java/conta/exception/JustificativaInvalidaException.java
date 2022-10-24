package conta.exception;

public class JustificativaInvalidaException extends RuntimeException {
    public JustificativaInvalidaException() {
        super("Justificativa invalida");
    }

    public JustificativaInvalidaException(String message) {
        super(message);
    }
}
