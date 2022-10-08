package com.mjvgrupo03.controlefinanceiro;

import com.mjvgrupo03.controlefinanceiro.exception.ContaInvalidaException;
import com.mjvgrupo03.controlefinanceiro.exception.DataInvalidaException;
import com.mjvgrupo03.controlefinanceiro.exception.JustificativaInvalidaException;
import com.mjvgrupo03.controlefinanceiro.exception.SaldoInvalidoException;

import java.time.LocalDate;

public class Main
{
    public static void main(String[] arguments)
    {
        ContaCorrente cliente1 = new ContaCorrente("Diego", 123, 456);
        ContaCorrente cliente2 = new ContaCorrente("Joao", 321, 654);
        LocalDate dataInicial = LocalDate.now();
        LocalDate dataFinal = dataInicial.plusMonths(1);

        try
        {
            cliente1.depositar(1000.0);
            cliente1.sacar(500.0);
            cliente1.transferir(500.0, cliente2);
            cliente1.cancelarConta("Justificativa");
            cliente1.consultarExtrato(dataInicial, dataFinal);
        }
        catch (JustificativaInvalidaException e)
        {
            e.printStackTrace();
        }
        catch (ContaInvalidaException e)
        {
            e.printStackTrace();
        }
        catch (DataInvalidaException e)
        {
            e.printStackTrace();
        }
        catch (SaldoInvalidoException e)
        {
            e.printStackTrace();
        }
    }
}
