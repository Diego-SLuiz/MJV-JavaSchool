package sistema;

import cliente.Cliente;
import conta.ContaCorrente;
import conta.Operacao;

import java.time.LocalDate;
import java.util.ArrayList;

public class SistemaBanco
{
    public static void main(String [] args)
    {
        Cliente pessoa1 = new Cliente("Diego", LocalDate.now(), "123456798");
        ContaCorrente conta1 = new ContaCorrente(pessoa1, 123, 321);

        Cliente pessoa2 = new Cliente("Jhon", LocalDate.now(), "123456798");
        ContaCorrente conta2 = new ContaCorrente(pessoa2, 456, 654);

        LocalDate dataInicial = LocalDate.now();
        LocalDate dataFinal = dataInicial.plusMonths(1);

        conta1.depositar(1000.0);
        conta1.sacar(500.0);
        conta1.transferir(500.0, conta2);
        conta1.cancelarConta("Sem dinheiro para guardar");
        ArrayList<Operacao> extrato = conta1.consultarExtrato(dataInicial, dataFinal);

        GeradorArquivoOperacoes.gerarArquivo(conta1);
    }
}
