package conta.exception;

public class JustificativaInvalidaException extends Exception
{
    public JustificativaInvalidaException()
    {
        super("Justificativa invalida");
    }

    public JustificativaInvalidaException(String message)
    {
        super(message);
    }
}
