import com.mjvgrupo03.controlefinanceiro.ContaCorrente;
import com.mjvgrupo03.controlefinanceiro.Extrato;
import com.mjvgrupo03.controlefinanceiro.exception.ContaInvalidaException;
import com.mjvgrupo03.controlefinanceiro.exception.DataInvalidaException;
import com.mjvgrupo03.controlefinanceiro.exception.JustificativaInvalidaException;
import com.mjvgrupo03.controlefinanceiro.exception.SaldoInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestandoContaCorrente
{
    ContaCorrente contaTeste;
    ContaCorrente contaSecundaria;
    ContaCorrente contaNula;

    @BeforeEach
    public void setUp()
    {
        contaTeste = new ContaCorrente("Teste", 123, 456);
        contaSecundaria = new ContaCorrente("Teste 2", 321, 654);
        contaNula = null;
    }

    @DisplayName("Testando o metodo saque")
    @Test
    public void testandoMetodoSaque() throws SaldoInvalidoException
    {
        contaTeste.depositar(500.0);
        contaTeste.sacar(375.0);

        // Validar que o saldo está sendo decrementado corretamente
        Double expectativa = 125.0;
        Double resultado = contaTeste.getSaldoDisponivel();
        Assertions.assertEquals(expectativa, resultado);

        // Validar que não é possível sacar além do saldo disponível
        Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTeste.sacar(10000.0));

        // Validar que não é possível sacar um valor menor ou igual à zero
        Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTeste.sacar(0.0));
    }

    @DisplayName("Testando o metodo deposito")
    @Test
    public void testandoMetodoDeposito() throws SaldoInvalidoException
    {
        contaTeste.depositar(100.0);

        // Validar que o saldo está sendo incrementado corretamente
        Double expectativa = 100.0;
        Double resultado = contaTeste.getSaldoDisponivel();
        Assertions.assertEquals(expectativa, resultado);

        // Validar que não é possível depositar um valor menor ou igual à zero
        Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTeste.depositar(0.0));
    }

    @DisplayName("Testando o metodo transferencia")
    @Test
    public void testandoMetodoTransferencia() throws SaldoInvalidoException, ContaInvalidaException
    {
        contaTeste.depositar(1000.0);
        contaTeste.transferir(900.0, contaSecundaria);

        // Validar que o valor da tranferência está sendo incrementado corretamente
        Double expectativa = 100.0;
        Double resultado = contaTeste.getSaldoDisponivel();
        Assertions.assertEquals(expectativa, resultado);

        // Validar que o valor da transferidor está tendo o saldo decrementado corretamente
        expectativa = 900.0;
        resultado = contaSecundaria.getSaldoDisponivel();
        Assertions.assertEquals(expectativa, resultado);

        // Validar que não é possível transferir para uma conta nula
        Assertions.assertThrows(ContaInvalidaException.class, () -> contaTeste.transferir(100.0, contaNula));

        // Validar que não é possível transferir um valor menor ou igual à zero
        Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTeste.transferir(0.0, contaSecundaria));

        // Validar que não é possível transferir um valor maior que o saldo disponível
        Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTeste.transferir(0.0, contaSecundaria));
    }

    @DisplayName("Testando o metodo cancelarConta")
    @Test
    public void testandoMetodoCancelarConta() throws JustificativaInvalidaException
    {
        contaTeste.cancelarConta("To sem dinheiro para guardar");

        // Validar que uma conta tem o atributo cancelada atualizado
        Boolean expectativa = true;
        Boolean resultado = contaTeste.getContaCancelada();
        Assertions.assertEquals(expectativa, resultado);

        // Validar que não é possível cancelar uma conta sem uma justificativa
        Assertions.assertThrows(JustificativaInvalidaException.class, () -> {contaTeste.cancelarConta("");});
    }

    @DisplayName("Testando o metodo consultarExtrato")
    @Test
    public void testandoMetodoConsultarExtrato() throws SaldoInvalidoException
    {
        contaTeste.depositar(1000.0);
        contaTeste.sacar(500.0);
        contaTeste.depositar(1000.0);
        contaTeste.sacar(500.0);

        // Validar que o extrato está sendo adicionado corretamente
        Integer expectativa = 4;
        Integer resultado = contaTeste.getListaExtratos().size();
        Assertions.assertEquals(expectativa, resultado);

        // Validar que a dataInicial deve ser menor que a dataFinal
        LocalDate dataInicial = LocalDate.now();
        LocalDate dataFinal = dataInicial.minusMonths(1);
        Assertions.assertThrows(DataInvalidaException.class, () -> contaTeste.consultarExtrato(dataInicial, dataFinal));
    }
}
