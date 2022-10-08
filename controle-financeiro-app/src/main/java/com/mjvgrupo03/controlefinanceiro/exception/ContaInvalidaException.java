package com.mjvgrupo03.controlefinanceiro.exception;

public class ContaInvalidaException extends Exception
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
