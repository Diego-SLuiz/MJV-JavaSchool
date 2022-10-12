package conta.exception;

public class DataInvalidaException extends Exception
{
    public DataInvalidaException()
    {
        super("Data invalida");
    }

    public DataInvalidaException(String message)
    {
        super(message);
    }
}
