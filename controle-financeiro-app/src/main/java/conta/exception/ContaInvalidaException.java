package conta.exception;

public class ContaInvalidaException extends RuntimeException
{
    public ContaInvalidaException()
    {
        super("Conta invalida");
    }

    public ContaInvalidaException(String message)
    {
        super(message);
    }
}
