package conta;

import cliente.Cliente;
import cliente.enumeration.TipoVinculo;
import conta.enumeration.MotivoCancelamento;
import conta.enumeration.StatusConta;
import conta.exception.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

public class ContaBancariaTestes {
    LocalDate dataTestePresente;
    LocalDate dataTesteFutura;
    Cliente clienteTestePrimario;
    Cliente clienteTesteSecundario;
    ContaCorrente contaTestePrimaria;
    ContaCorrente contaTesteSecundaria;

    @BeforeEach
    public void setUp() {
        dataTestePresente = LocalDate.now();
        dataTesteFutura = dataTestePresente.plusMonths(1);

        clienteTestePrimario = new Cliente("Cliente 1", TipoVinculo.CNPJ, "00000123456789");
        clienteTesteSecundario = new Cliente("Cliente 2", TipoVinculo.CPF, "00321654987");

        contaTestePrimaria = new ContaCorrente(12345, 41526);
        contaTesteSecundaria = new ContaCorrente(54321, 14253);
    }

    @Nested
    @DisplayName("Testando as funcionalidades do metodo sacar")
    public class TestandoMetodoSacar {
        @Test
        @DisplayName("Tentativa de saque utilizando valor negativo causa exception ValorInvalidoException")
        public void sacarValorNegativoCausaException() {
            contaTestePrimaria.depositar(100.0);
            Assertions.assertThrows(ValorInvalidoException.class, () -> contaTestePrimaria.sacar(-1.0));
        }

        @Test
        @DisplayName("Tentativa de saque utilizando valor zerado causa exception ValorInvalidoException")
        public void sacarValorZeradoCausaException() {
            contaTestePrimaria.depositar(100.0);
            Assertions.assertThrows(ValorInvalidoException.class, () -> contaTestePrimaria.sacar(0.0));
        }

        @Test
        @DisplayName("Tentativa de saque utilizando valor nulo causa exception ValorInvalidoException")
        public void sacarValorNuloCausaException() {
            contaTestePrimaria.depositar(100.0);
            Assertions.assertThrows(ValorInvalidoException.class, () -> contaTestePrimaria.sacar(null));
        }

        @Test
        @DisplayName("Tentativa de saque utilizando valor maior que o saldo disponivel causa exception SaldoInsuficienteException")
        public void sacarValorExcessivoCausaException() {
            contaTestePrimaria.depositar(100.0);
            Assertions.assertThrows(SaldoInsuficienteException.class, () -> contaTestePrimaria.sacar(10000.0));
        }

        @Test
        @DisplayName("Tentativa de saque utilizando uma conta cancelada causa exception ContaInvalidaException")
        public void sacarUtilizandoContaCanceladaCausaException() {
            contaTestePrimaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.sacar(1.0));
        }

        @Test
        @DisplayName("Validar que o saldo disponivel esta sendo decrementado corretamente ao sacar")
        public void sacarValorDecrementaSaldoDisponivel() {
            contaTestePrimaria.depositar(100.0);
            contaTestePrimaria.sacar(100.0);
            Double expectativa = 0.0;
            Double resultado = contaTestePrimaria.getSaldoDisponivel();
            Assertions.assertEquals(expectativa, resultado);
        }
    }

    @Nested
    @DisplayName("Testando as funcionalidades do metodo depositar")
    public class TestandoMetodoDepositar {
        @Test
        @DisplayName("Tentativa de deposito utilizando valor negativo causa exception ValorInvalidoException")
        public void depositarValorNegativoCausaException() {
            Assertions.assertThrows(ValorInvalidoException.class, () -> contaTestePrimaria.depositar(-1.0));
        }

        @Test
        @DisplayName("Tentativa de deposito utilizando valor zerado causa exception ValorInvalidoException")
        public void depositarValorZeradoCausaException() {
            Assertions.assertThrows(ValorInvalidoException.class, () -> contaTestePrimaria.depositar(0.0));
        }

        @Test
        @DisplayName("Tentativa de deposito utilizando valor nulo causa exception ValorInvalidoException")
        public void depositarValorNuloCausaException() {
            Assertions.assertThrows(ValorInvalidoException.class, () -> contaTestePrimaria.depositar(null));
        }

        @Test
        @DisplayName("Tentativa de deposito utilizando uma conta cancelada causa uma exception ContaInvalidaException")
        public void depositarUtilizandoContaCanceladaCausaException() {
            contaTestePrimaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.depositar(1.0));
        }

        @Test
        @DisplayName("Validar que o saldo disponivel esta sendo incrementado corretamente ao depositar")
        public void depositarValorIncrementaSaldoDisponivel() {
            contaTestePrimaria.depositar(100.0);
            contaTestePrimaria.depositar(50.0);
            Double expectativa = 150.0;
            Double resultado = contaTestePrimaria.getSaldoDisponivel();
            Assertions.assertEquals(expectativa, resultado);
        }
    }

    @Nested
    @DisplayName("Testando as funcionalidades do metodo transferir")
    public class TestandoMetodoTransferir {
        @Test
        @DisplayName("Tentativa de transferencia utilizando valor negativo causa exception ValorInvalidoException")
        public void transferirValorNegativoCausaException() {
            contaTestePrimaria.depositar(100.0);
            Assertions.assertThrows(ValorInvalidoException.class, () -> contaTestePrimaria.transferir(-1.0, contaTesteSecundaria));
        }

        @Test
        @DisplayName("Tentativa de transferencia utilizando valor zerado causa exception ValorInvalidoException")
        public void transferirValorZeradoCausaException() {
            contaTestePrimaria.depositar(100.0);
            Assertions.assertThrows(ValorInvalidoException.class, () -> contaTestePrimaria.transferir(0.0, contaTesteSecundaria));
        }

        @Test
        @DisplayName("Tentativa de transferencia utilizando valor nulo causa exception ValorInvalidoException")
        public void transferirValorNuloCausaException() {
            contaTestePrimaria.depositar(100.0);
            Assertions.assertThrows(ValorInvalidoException.class, () -> contaTestePrimaria.transferir(null, contaTesteSecundaria));
        }

        @Test
        @DisplayName("Tentativa de transferencia utilizando valor maior que o saldo disponivel causa exception SaldoInsuficienteException")
        public void transferirValorExcessivoCausaException() {
            contaTestePrimaria.depositar(100.0);
            Assertions.assertThrows(SaldoInsuficienteException.class, () -> contaTestePrimaria.transferir(10000.0, contaTesteSecundaria));
        }

        @Test
        @DisplayName("Tentativa de transferencia utilizando uma conta cancelada causa exception ContaInvalidaException")
        public void transferirUtilizandoContaCanceladaCausaException() {
            contaTestePrimaria.depositar(100.0);
            contaTestePrimaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.transferir(1.0, contaTesteSecundaria));
        }

        @Test
        @DisplayName("Tentativa de transferencia para uma conta nula causa exception ContaInvalidaException")
        public void transferirParaContaNulaCausaException() {
            contaTestePrimaria.depositar(100.0);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.transferir(50.0, null));
        }

        @Test
        @DisplayName("Tentativa de transferencia para uma conta cancelada causa exception ContaInvalidaException")
        public void transferirParaContaCanceladaCausaException() {
            contaTestePrimaria.depositar(100.0);
            contaTesteSecundaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.transferir(50.0, contaTesteSecundaria));
        }

        @Test
        @DisplayName("Tentativa de transferencia para a mesma conta causa uma exception ContaInvalidaException")
        public void transferirParaMesmaContaCausaException() {
            contaTestePrimaria.depositar(100.0);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.transferir(100.0, contaTestePrimaria));
        }

        @Test
        @DisplayName("Validar que uma transferencia decrementa o saldo disponivel da conta que transfere")
        public void transferirDecrementaSaldoDisponivelRemetente() {
            contaTestePrimaria.depositar(100.0);
            contaTestePrimaria.transferir(50.0, contaTesteSecundaria);
            contaTestePrimaria.transferir(25.0, contaTesteSecundaria);
            Double expectativa = 25.0;
            Double resultado = contaTestePrimaria.getSaldoDisponivel();
            Assertions.assertEquals(expectativa, resultado);
        }

        @Test
        @DisplayName("Validar que uma transferencia incrementa o saldo disponivel da conta que recebe")
        public void transferirIncrementaSaldoDisponivelDestinatario() {
            contaTestePrimaria.depositar(100.0);
            contaTestePrimaria.transferir(50.0, contaTesteSecundaria);
            contaTestePrimaria.transferir(25.0, contaTesteSecundaria);
            Double expectativa = 75.0;
            Double resultado = contaTesteSecundaria.getSaldoDisponivel();
            Assertions.assertEquals(expectativa, resultado);
        }
    }

    @Nested
    @DisplayName("Testando as funcionalidades do metodo cancelarConta")
    public class TestandoMetodoCancelarConta {
        @Test
        @DisplayName("Tentativa de cancelamento com uma justificativa vazia causa exception JustificativaInvalidaException")
        public void cancelarContaJustificativaVaziaCausaException() {
            Assertions.assertThrows(JustificativaInvalidaException.class, () -> contaTestePrimaria.cancelarConta(" ", MotivoCancelamento.INSATISFEITO));
        }

        @Test
        @DisplayName("Tentativa de cancelamento com uma justificativa nula causa exception JustificativaInvalidaException")
        public void cancelarContaJustificativaNulaCausaException() {
            Assertions.assertThrows(JustificativaInvalidaException.class, () -> contaTestePrimaria.cancelarConta(null, MotivoCancelamento.INSATISFEITO));
        }

        @Test
        @DisplayName("Tentativa de cancelamento utilizando uma conta cancelada causa exception ContaInvalidaException")
        public void cancelarContaCanceladaCausaException() {
            contaTestePrimaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO));
        }

        @Test
        @DisplayName("Validar que o statusConta e atualizado corretamente ao cancelar uma conta")
        public void cancelarContaAtualizaAtributoCancelada() {
            contaTestePrimaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO);
            Assertions.assertEquals(contaTestePrimaria.getStatusConta(), StatusConta.CANCELADA);
        }
    }

    @Nested
    @DisplayName("Testando as funcionalidades do metodo consultarExtrato")
    public class TestandoMetodoConsultarExtrato {
        @Test
        @DisplayName("Tentativa de consultar extrato de uma conta cancelada causa exception ContaInvalidaException")
        public void consultarExtratoContaCanceladaCausaException() {
            contaTestePrimaria.cancelarConta("Teste", MotivoCancelamento.INSATISFEITO);
            Assertions.assertThrows(ContaInvalidaException.class, () -> contaTestePrimaria.consultarExtrato(dataTestePresente, dataTesteFutura));
        }

        @Test
        @DisplayName("Tentativa de consultar extrato com uma data inicial nula causa exception DataInvalidaException")
        public void consultarExtratoDataInicialNulaCausaException() {
            Assertions.assertThrows(DataInvalidaException.class, () -> contaTestePrimaria.consultarExtrato(null, dataTesteFutura));
        }

        @Test
        @DisplayName("Tentativa de consultar extrato com uma data final nula causa uma exception DataInvalidaException")
        public void consultarExtratoDataFinalNulaCausaException() {
            Assertions.assertThrows(DataInvalidaException.class, () -> contaTestePrimaria.consultarExtrato(dataTestePresente, null));
        }

        @Test
        @DisplayName("Tentativa de consultar extrato com uma data inicial posterior a final causa uma exception DataInvalidaException")
        public void consultarExtratoDataInicialPosteriorFinalCausaException() {
            Assertions.assertThrows(DataInvalidaException.class, () -> contaTestePrimaria.consultarExtrato(dataTesteFutura, dataTestePresente));
        }

        @Test
        @DisplayName("Validar que a quantidade de extratos retornado e correta")
        public void consultarExtratoRetornaExtratoCorretamente() {
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
