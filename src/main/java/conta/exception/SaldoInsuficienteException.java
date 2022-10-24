package conta.exception;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException() {
        super("Saldo invalido");
    }

    public SaldoInsuficienteException(String message) {
        super(message);
    }
}
