package sistema;

import cliente.Cliente;
import conta.ContaCorrente;

import java.time.LocalDate;

public class SistemaBanco
{
    public static void main(String [] args)
    {
        Cliente pessoa1 = new Cliente("Diego", LocalDate.now(), "123456798");
        ContaCorrente conta1 = new ContaCorrente(pessoa1, 123, 321);

        Cliente pessoa2 = new Cliente("Jhon", LocalDate.now(), "123456798");
        ContaCorrente conta2 = new ContaCorrente(pessoa2, 456, 654);

        try
        {
            conta1.depositar(1000.0);
            conta1.sacar(500.0);
            conta1.transferir(500.0, conta2);
        }
        catch (Exception error)
        {
            throw new RuntimeException(error);
        }

    }
}
