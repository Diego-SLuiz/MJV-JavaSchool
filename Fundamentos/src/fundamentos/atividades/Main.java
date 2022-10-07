package fundamentos.atividades;

import java.time.LocalDate;

public class Main
{
    public static void main(String [] arguments)
    {
        ContaCorrente cliente1 = new ContaCorrente("Diego", 123, 456);
        ContaCorrente cliente2 = new ContaCorrente("Joao", 321, 654);
        LocalDate dataInicial = LocalDate.now();
        LocalDate dataFinal = dataInicial.plusMonths(1);

        cliente1.depositar(1000.0);
        cliente1.sacar(500.0);
        cliente1.transferir(500.0, cliente2);
        cliente1.consultarExtrato(dataInicial, dataFinal);
        cliente1.cancelarConta("To sem dinheiro :(");
    }
}
