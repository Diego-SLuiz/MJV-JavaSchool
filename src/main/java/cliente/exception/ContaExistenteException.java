package cliente.exception;

public class ContaExistenteException extends RuntimeException {
    public ContaExistenteException() {
        super("Conta existente");
    }

    public ContaExistenteException(String message) {
        super(message);
    }
}
