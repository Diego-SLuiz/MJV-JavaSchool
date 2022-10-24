package conta;

import conta.enumeration.StatusConta;
import conta.enumeration.TipoOperacao;
import conta.exception.ContaInvalidaException;
import conta.exception.DataInvalidaException;
import conta.exception.SaldoInsuficienteException;
import conta.exception.ValorInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class ContaBancaria {
    protected LocalDate dataAbertura;
    protected Integer numeroConta;
    protected Integer numeroAgencia;
    protected StatusConta statusConta;
    protected ArrayList<Operacao> historicoOperacoes;
    protected Cancelamento cancelamento;
    protected Double saldoDisponivel;

    protected static void validarConta(ContaBancaria conta) {
        if (conta == null || conta.statusConta == StatusConta.CANCELADA)
            throw new ContaInvalidaException("Uma ContaBancaria ativa e necessaria");
    }

    protected static void validarConta(ContaBancaria primeiraConta, ContaBancaria segundaConta) {
        ContaBancaria.validarConta(primeiraConta);
        ContaBancaria.validarConta(segundaConta);

        if (primeiraConta.numeroConta == segundaConta.numeroConta) {
            StringBuilder mensagem = new StringBuilder();
            mensagem.append("As contas precisam ser diferentes\n");
            mensagem.append(String.format("primeiraConta: %s\n", primeiraConta.numeroConta));
            mensagem.append(String.format("segundaConta: %s\n", segundaConta.numeroConta));
            throw new ContaInvalidaException(mensagem.toString());
        }
    }

    protected static void validarValor(Double valor) {
        if (valor == null || valor <= 0)
            throw new ValorInvalidoException(String.format("Um valor Double positivo e necessario: %s", valor));
    }

    protected static void validarSaldo(ContaBancaria conta, Double valor) {
        ContaBancaria.validarConta(conta);
        ContaBancaria.validarValor(valor);

        if (valor > conta.saldoDisponivel) {
            StringBuilder mensagem = new StringBuilder();
            mensagem.append("O valor precisa ser inferior ao saldo disponivel\n");
            mensagem.append(String.format("Saldo: %s\n", conta.saldoDisponivel));
            mensagem.append(String.format("Valor: %s\n", valor));
            throw new SaldoInsuficienteException(mensagem.toString());
        }
    }

    protected static void validarData(LocalDate dataInicial, LocalDate dataFinal) {
        if (dataInicial == null || dataFinal == null || dataInicial.isAfter(dataFinal)) {
            StringBuilder mensagem = new StringBuilder();
            mensagem.append("A dataInicial precisa ser anterior a dataFinal\n");
            mensagem.append(String.format("dataInicial: %s\n", dataInicial));
            mensagem.append(String.format("dataFinal: %s\n", dataFinal));
            throw new DataInvalidaException(mensagem.toString());
        }
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public Integer getNumeroAgencia() {
        return numeroAgencia;
    }

    public StatusConta getStatusConta() {
        return statusConta;
    }

    public ArrayList<Operacao> getHistoricoOperacoes() {
        return historicoOperacoes;
    }

    public Cancelamento getCancelamento() {
        return cancelamento;
    }

    public Double getSaldoDisponivel() {
        return saldoDisponivel;
    }

    protected void adicionarHistorico(String descricao, Double valor, TipoOperacao tipo) {
        Operacao operacao = new Operacao(descricao, valor, tipo);
        this.historicoOperacoes.add(operacao);
    }
}
