package com.mjvgrupo03.controlefinanceiro.exception;

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
