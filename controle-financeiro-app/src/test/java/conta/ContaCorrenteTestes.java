package conta;

import cliente.Cliente;
import conta.ContaCorrente;
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
    Cliente clienteTesteNulo;
    ContaCorrente contaTestePrimaria;
    ContaCorrente contaTesteSecundaria;
    ContaCorrente contaTesteNula;

    @BeforeEach
    public void setUp()
    {
        this.dataTestePresente = LocalDate.now();
        this.dataTesteFutura = this.dataTestePresente.plusMonths(1);

        this.clienteTestePrimario = new Cliente("Cliente 1", dataTestePresente, "00123456789");
        this.clienteTesteSecundario = new Cliente("Cliente 2", dataTestePresente, "00321654987");
        this.clienteTesteNulo = null;

        this.contaTestePrimaria = new ContaCorrente(this.clienteTestePrimario, 123, 456);
        this.contaTesteSecundaria = new ContaCorrente(this.clienteTesteSecundario, 321, 456);
        this.contaTesteNula = null;
    }

    @Nested
    @DisplayName("Testando as funcionalidades do metodo sacar")
    public class TestandoMetodoSacar
    {
        @Test
        @DisplayName("Sacar com um valor igual a zero resulta em erro")
        public void SacarValorNegativoCausaException()
        {

        }
    }

    @Nested
    @DisplayName("Testando as funcionalidades do metodo depositar")
    public class TestandoMetodoDepositar
    {

    }

    @Nested
    @DisplayName("Testando as funcionalidades do metodo transferir")
    public class TestandoMetodoTransferir
    {

    }

    @Nested
    @DisplayName("Testando as funcionalidades do metodo cancelarConta")
    public class TestandoMetodoCancelarConta
    {

    }

    @Nested
    @DisplayName("Testando as funcionalidades do metodo consultarExtrato")
    public class TestandoMetodoConsultarExtrato
    {

    }
}
