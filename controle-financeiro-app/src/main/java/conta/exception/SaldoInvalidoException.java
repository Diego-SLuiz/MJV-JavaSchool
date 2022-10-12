package conta.exception;

public class SaldoInvalidoException extends RuntimeException
{
    public SaldoInvalidoException()
    {
        super("Saldo invalido");
    }

    public SaldoInvalidoException(String message)
    {
        super(message);
    }
}
