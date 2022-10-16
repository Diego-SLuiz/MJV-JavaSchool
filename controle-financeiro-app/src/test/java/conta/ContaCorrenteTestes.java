package conta;

import cliente.Cliente;

import conta.enumeration.MotivoCancelamento;
import conta.exception.ContaInvalidaException;
import conta.exception.DataInvalidaException;
import conta.exception.JustificativaInvalidaException;
import conta.exception.SaldoInvalidoException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.time.LocalDate;

public class ContaCorrenteTestes
{
    LocalDate dataTestePresente;
    LocalDate dataTesteFutura;
    Cliente clienteTestePrimario;
    Cliente clienteTesteSecundario;
    ContaCorrente contaTestePrimaria;
    ContaCorrente contaTesteSecundaria;

    @BeforeEach
    public void setUp()
    {
        dataTestePresente = LocalDate.now();
        dataTesteFutura = dataTestePresente.plusMonths(1);

        clienteTestePrimario = new Cliente("Cliente 1", dataTestePresente, "00123456789");
        clienteTesteSecundario = new Cliente("Cliente 2", dataTestePresente, "00321654987");

        contaTestePrimaria = new ContaCorrente(clienteTestePrimario, 123, 456);
        contaTesteSecundaria = new ContaCorrente(clienteTesteSecundario, 321, 456);
    }

    @Nested
    @DisplayName("Testando as funcionalidades do metodo sacar")
    public class TestandoMetodoSacar
    {
        @Test
        @DisplayName("Sacar com um valor negativo causa uma exception SaldoInvalidoException")
        public void sacarValorNegativoCausaException()
        {
            contaTestePrimaria.depositar(100.0);
            Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTestePrimaria.sacar(-1.0));
        }

        @Test
        @DisplayName("Sacar com um valor igual a zero causa uma exception SaldoInvalidoExeption")
        public void sacarValorZeradoCausaException()
        {
            Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTestePrimaria.sacar(0.0));
        }

        @Test
        @DisplayName("Sacar com um valor nulo causa uma exception SaldoInvalidoException")
        public void sacarValorNuloCausaException()
        {
            Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTestePrimaria.sacar(null));
        }

        @Test
        @DisplayName("Sacar com um valor maior que o saldo disponivel causa uma exception SaldoInvalidoException")
        public void sacarValorExcessivoCausaException()
        {
            Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTestePrimaria.sacar(10000.0));
        }

        @Test
        @DisplayName("Sacar valor utilizando uma conta cancelada causa uma exception ContaInvalidaException")
        public void sacarUtilizandoContaCanceladaCausaException()
        {
            contaTestePrimaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.sacar(1.0));
        }

        @Test
        @DisplayName("Validar que o saldo disponivel esta sendo decrementado corretamente ao sacar")
        public void sacarValorDecrementaSaldoDisponivel()
        {
            contaTestePrimaria.depositar(100.0);
            contaTestePrimaria.sacar(100.0);
            Double expectativa = 0.0;
            Double resultado = contaTestePrimaria.getSaldoDisponivel();
            Assertions.assertEquals(expectativa, resultado);
        }
    }

    @Nested
    @DisplayName("Testando as funcionalidades do metodo depositar")
    public class TestandoMetodoDepositar
    {
        @Test
        @DisplayName("Depositar com uma valor negativo causa uma exception SaldoInvalidoException")
        public void depositarValorNegativoCausaException()
        {
            Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTestePrimaria.depositar(-1.0));
        }

        @Test
        @DisplayName("Depositar com um valor zerado causa uma exception SaldoInvalidoException")
        public void depositarValorZeradoCausaException()
        {
            Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTestePrimaria.depositar(0.0));
        }

        @Test
        @DisplayName("Depositar com um valor nulo causa uma exception SaldoInvalidoException")
        public void depositarValorNuloCausaException()
        {
            Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTestePrimaria.depositar(null));
        }

        @Test
        @DisplayName("Depositar valor utilizando uma conta cancelada causa uma exception ContaInvalidaException")
        public void depositarUtilizandoContaCanceladaCausaException()
        {
            contaTestePrimaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.depositar(1.0));
        }

        @Test
        @DisplayName("Validar que o saldo disponivel esta sendo incrementado corretamente ao depositar")
        public void depositarValorIncrementaSaldoDisponivel()
        {
            contaTestePrimaria.depositar(100.0);
            Double expectativa = 100.0;
            Double resultado = contaTestePrimaria.getSaldoDisponivel();
            Assertions.assertEquals(expectativa, resultado);
        }
    }

    @Nested
    @DisplayName("Testando as funcionalidades do metodo transferir")
    public class TestandoMetodoTransferir
    {
        @Test
        @DisplayName("Transferir com um valor negativo causa uma exception SaldoInvalidoException")
        public void transferirValorNegativoCausaException()
        {
            Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTestePrimaria.transferir(-1.0, contaTesteSecundaria));
        }

        @Test
        @DisplayName("Transferir com um valor zerado causa uma exception SaldoInvalidoException")
        public void transferirValorZeradoCausaException()
        {
            Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTestePrimaria.transferir(0.0, contaTesteSecundaria));
        }

        @Test
        @DisplayName("Transferir com um valor nulo causa uma exception SaldoInvalidoException")
        public void transferirValorNuloCausaException()
        {
            Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTestePrimaria.transferir(null, contaTesteSecundaria));
        }

        @Test
        @DisplayName("Transferir com uma valor maior que o saldo disponivel causa uma exception SaldoInvalidoException")
        public void transferirValorExcessivoCausaException()
        {
            Assertions.assertThrows(SaldoInvalidoException.class, () -> contaTestePrimaria.transferir(10000.0, contaTesteSecundaria));
        }

        @Test
        @DisplayName("Transferir utilizando uma conta cancelada causa uma exception ContaInvalidaException")
        public void transferirUtilizandoContaCanceladaCausaException()
        {
            contaTestePrimaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.transferir(1.0, contaTesteSecundaria));
        }

        @Test
        @DisplayName("Transferir para uma conta cancelada causa uma exception ContaInvalidaException")
        public void transferirParaContaCanceladaCausaException()
        {
            contaTestePrimaria.depositar(100.0);
            contaTesteSecundaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.transferir(50.0, contaTesteSecundaria));
        }

        @Test
        @DisplayName("Transferir para uma conta nula causa uma exception ContaInvalidaException")
        public void transferirParaContaNulaCausaException()
        {
            contaTestePrimaria.depositar(100.0);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.transferir(50.0, null));
        }

        @Test
        @DisplayName("Validar que uma transferencia decrementa o saldo disponivel da conta que transfere")
        public void transferirDecrementaSaldoDisponivelRemetente()
        {
            contaTestePrimaria.depositar(100.0);
            contaTestePrimaria.transferir(50.0, contaTesteSecundaria);
            Double expectativa = 50.0;
            Double resultado = contaTestePrimaria.getSaldoDisponivel();
            Assertions.assertEquals(expectativa, resultado);
        }

        @Test
        @DisplayName("Validar que uma transferencia incrementa o saldo disponivel da conta que recebe")
        public void transferirIncrementaSaldoDisponivelDestinatario()
        {
            contaTestePrimaria.depositar(100.0);
            contaTestePrimaria.transferir(75.0, contaTesteSecundaria);
            Double expectativa = 75.0;
            Double resultado = contaTesteSecundaria.getSaldoDisponivel();
            Assertions.assertEquals(expectativa, resultado);
        }

        @Test
        @DisplayName("Transferir para a mesma conta causa uma exception ContaInvalidaException")
        public void transferirParaMesmaContaCausaException()
        {
            contaTestePrimaria.depositar(100.0);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.transferir(100.0, contaTestePrimaria));
        }
    }

    @Nested
    @DisplayName("Testando as funcionalidades do metodo cancelarConta")
    public class TestandoMetodoCancelarConta
    {
        @Test
        @DisplayName("Cancelar conta com uma justificativa vazia causa uma exception JustificativaInvalidaException")
        public void cancelarContaJustificativaVaziaCausaException()
        {
            Assertions.assertThrows(JustificativaInvalidaException.class, () -> contaTestePrimaria.cancelarConta(" ", MotivoCancelamento.INSATISFEITO));
        }

        @Test
        @DisplayName("Cancelar conta com uma justificativa nula causa uma exception JustificativaInvalidaException")
        public void cancelarContaJustificativaNulaCausaException()
        {
            Assertions.assertThrows(JustificativaInvalidaException.class, () -> contaTestePrimaria.cancelarConta(null, MotivoCancelamento.INSATISFEITO));
        }

        @Test
        @DisplayName("Cancelar conta que ja esta cancelada causa exception ContaInvalidaException")
        public void cancelarContaCanceladaCausaExeption()
        {
            contaTestePrimaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO));
        }

        @Test
        @DisplayName("Validar que o atributo cancelada e atualizado corretamente ao cancelar uma conta")
        public void cancelarContaAtualizaAtributoCancelada()
        {
            contaTestePrimaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO);
            Assertions.assertTrue(contaTestePrimaria.getContaCancelada());
        }
    }

    @Nested
    @DisplayName("Testando as funcionalidades do metodo consultarExtrato")
    public class TestandoMetodoConsultarExtrato
    {
        @Test
        @DisplayName("Consultar extrato de uma conta cancelada causa uma exception ContaInvalidaException")
        public void consultarExtratoContaCanceladaCausaException()
        {
            contaTestePrimaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.consultarExtrato(dataTestePresente, dataTesteFutura));
        }

        @Test
        @DisplayName("Consultar extrato com uma data inicial nula causa uma exception DataInvalidaException")
        public void consultarExtratoDataInicialNulaCausaException()
        {
            Assertions.assertThrows(DataInvalidaException.class, () -> contaTestePrimaria.consultarExtrato(null, dataTesteFutura));
        }

        @Test
        @DisplayName("Consultar extrato com uma data final nula causa uma exception DataInvalidaException")
        public void consultarExtratoDataFinalNulaCausaException()
        {
            Assertions.assertThrows(DataInvalidaException.class, () -> contaTestePrimaria.consultarExtrato(dataTestePresente, null));
        }

        @Test
        @DisplayName("Consultar extrato com uma data inicial posterior a final causa uma exception DataInvalidaException")
        public void consultarExtratoDataInicialPosteriorFinalCausaException()
        {
            Assertions.assertThrows(DataInvalidaException.class, () -> contaTestePrimaria.consultarExtrato(dataTesteFutura, dataTestePresente));
        }

        @Test
        @DisplayName("Validar que o extrato esta sendo retornado corretamente")
        public void consultarExtratoRetornaExtratoCorretamente()
        {
            contaTestePrimaria.depositar(200.0);
            contaTestePrimaria.depositar(200.0);
            contaTestePrimaria.sacar(200.0);
            contaTestePrimaria.sacar(200.0);
            Integer expectativa = 4;
            Integer resultado = contaTestePrimaria.consultarExtrato(dataTestePresente, dataTesteFutura).size();
            Assertions.assertEquals(expectativa, resultado);
        }
    }
}
