import com.mjvgrupo03.controlefinanceiro.ContaCorrente;
import com.mjvgrupo03.controlefinanceiro.Extrato;
import com.mjvgrupo03.controlefinanceiro.exception.ContaInvalidaException;
import com.mjvgrupo03.controlefinanceiro.exception.DataInvalidaException;
import com.mjvgrupo03.controlefinanceiro.exception.JustificativaInvalidaException;
import com.mjvgrupo03.controlefinanceiro.exception.SaldoInvalidoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestandoContaCorrente
{
    @DisplayName("Testando o metodo saque")
    @Test
    public void testandoMetodoSaque()
    {
        ContaCorrente contaTeste = new ContaCorrente("Teste", 123, 456);

        try
        {
            contaTeste.depositar(500.0);
            contaTeste.sacar(375.0);
        }
        catch (SaldoInvalidoException e)
        {
            throw new RuntimeException(e);
        }

        // Validar que o saldo está sendo decrementado corretamente
        Double expectativa = 125.0;
        Double resultado = contaTeste.getSaldoDisponivel();
        Assertions.assertEquals(expectativa, resultado);

        // Validar que não é possível sacar além do saldo disponível
        Assertions.assertThrows(SaldoInvalidoException.class, () -> {contaTeste.sacar(10000.0);});

        // Validar que não é possível sacar um valor menor ou igual à zero
        Assertions.assertThrows(SaldoInvalidoException.class, () -> {contaTeste.sacar(0.0);});

        // Exemplo de um saque com o valor correto que falha o teste, pois não dispara a exception esperada
        // Assertions.assertThrows(SaldoInvalidoException.class, () -> {contaTeste.sacar(10.0);});
    }

    @DisplayName("Testando o metodo deposito")
    @Test
    public void testandoMetodoDeposito()
    {
        ContaCorrente contaTeste = new ContaCorrente("Teste", 123, 456);

        try
        {
            contaTeste.depositar(100.0);
        }
        catch (SaldoInvalidoException e)
        {
            throw new RuntimeException(e);
        }

        // Validar que o saldo está sendo incrementado corretamente
        Double expectativa = 100.0;
        Double resultado = contaTeste.getSaldoDisponivel();
        Assertions.assertEquals(expectativa, resultado);

        // Validar que não é possível depositar um valor menor ou igual à zero
        Assertions.assertThrows(SaldoInvalidoException.class, () -> {contaTeste.depositar(0.0);});

        // Exemplo de um deposito com o valor correto que falha o teste, pois não dispara a exception esperada
        // Assertions.assertThrows(SaldoInvalidoException.class, () -> {contaTeste.depositar(100.0);});
    }

    @DisplayName("Testando o metodo transferencia")
    @Test
    public void testandoMetodoTransferencia()
    {
        ContaCorrente primeiraConta = new ContaCorrente("Teste 1", 123, 456);
        ContaCorrente segundaConta = new ContaCorrente("Teste 2", 321, 654);
        ContaCorrente contaNula = null;

        try
        {
            primeiraConta.depositar(1000.0);
            primeiraConta.transferir(900.0, segundaConta);
        }
        catch (SaldoInvalidoException e)
        {
            throw new RuntimeException(e);
        }
        catch (ContaInvalidaException e)
        {
            throw new RuntimeException(e);
        }

        // Validar que o valor da tranferência está sendo incrementado corretamente
        Double expectativa = 100.0;
        Double resultado = primeiraConta.getSaldoDisponivel();
        Assertions.assertEquals(expectativa, resultado);

        // Validar que o valor da transferidor está tendo o saldo decrementado corretamente
        expectativa = 900.0;
        resultado = segundaConta.getSaldoDisponivel();
        Assertions.assertEquals(expectativa, resultado);

        // Validar que não é possível transferir para uma conta nula
        Assertions.assertThrows(ContaInvalidaException.class, () -> {primeiraConta.transferir(100.0, contaNula);});

        // Validar que não é possível transferir um valor menor ou igual à zero
        Assertions.assertThrows(SaldoInvalidoException.class, () -> {primeiraConta.transferir(0.0, segundaConta);});

        // Validar que não é possível transferir um valor maior que o saldo disponível
        Assertions.assertThrows(SaldoInvalidoException.class, () -> {primeiraConta.transferir(0.0, segundaConta);});

        // Exemplo de uma transferência com o valor correto que falha o teste, pois não dispara a exception esperada
        // Assertions.assertThrows(SaldoInvalidoException.class, () -> {primeiraConta.transferir(10.0, segundaConta);});

        // Exemplo de uma transferência com a conta correta que falha o teste, pois não dispara a exception esperada
        // Assertions.assertThrows(ContaInvalidaException.class, () -> {primeiraConta.transferir(10.0, segundaConta);});
    }

    @DisplayName("Testando o metodo cancelarConta")
    @Test
    public void testandoMetodoCancelarConta()
    {
        ContaCorrente contaTeste = new ContaCorrente("Teste", 123, 456);

        try
        {
            contaTeste.cancelarConta("To sem dinheiro para guardar");
        }
        catch (JustificativaInvalidaException e)
        {
            throw new RuntimeException(e);
        }

        // Validar que uma conta tem o atributo cancelada atualizado
        Boolean expectativa = true;
        Boolean resultado = contaTeste.getContaCancelada();
        Assertions.assertEquals(expectativa, resultado);

        // Validar que não é possível cancelar uma conta sem uma justificativa
        Assertions.assertThrows(JustificativaInvalidaException.class, () -> {contaTeste.cancelarConta("");});

        // Exemplo de um cancelamento com a uma justificativa correta que falha o teste, pois não dispara a exception esperada
        // Assertions.assertThrows(JustificativaInvalidaException.class, () -> {contaTeste.cancelarConta("Teste");});
    }

    @DisplayName("Testando o metodo consultarExtrato")
    @Test
    public void testandoMetodoConsultarExtrato()
    {
        ContaCorrente contaTeste = new ContaCorrente("Teste", 123, 456);

        try
        {
            contaTeste.depositar(1000.0);
            contaTeste.sacar(500.0);
            contaTeste.depositar(1000.0);
            contaTeste.sacar(500.0);
        }
        catch (SaldoInvalidoException e)
        {
            throw new RuntimeException(e);
        }

        // Validar que o extrato está sendo adicionado corretamente
        Integer expectativa = 4;
        Integer resultado = contaTeste.getListaExtratos().size();
        Assertions.assertEquals(expectativa, resultado);

        // Validar que a dataInicial deve ser menor que a dataFinal
        LocalDate dataInicial = LocalDate.now();
        LocalDate dataFinal = dataInicial.minusMonths(1);
        Assertions.assertThrows(DataInvalidaException.class, () -> {contaTeste.consultarExtrato(dataInicial, dataFinal);});

        // Exemplo de uma consulta de extrato com a uma data correta que falha o teste, pois não dispara a exception esperada
        // Assertions.assertThrows(DataInvalidaException.class, () -> {contaTeste.consultarExtrato(dataFinal, dataInicial);});
    }
}
