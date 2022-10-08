package com.mjvgrupo03.controlefinanceiro.exception;

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
