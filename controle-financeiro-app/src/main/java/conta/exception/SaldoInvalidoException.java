package conta.exception;

public class SaldoInvalidoException extends Exception
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
