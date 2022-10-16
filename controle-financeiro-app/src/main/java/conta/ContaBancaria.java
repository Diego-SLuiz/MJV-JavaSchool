package conta;

import cliente.Cliente;
import conta.enumeration.MotivoCancelamento;
import conta.enumeration.TipoOperacao;
import conta.exception.ContaInvalidaException;
import conta.exception.DataInvalidaException;
import conta.exception.JustificativaInvalidaException;
import conta.exception.SaldoInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContaBancaria {
    protected Cliente cliente;
    protected Integer numeroConta;
    protected Integer numeroAgencia;
    protected Double saldoDisponivel;
    protected Boolean contaCancelada;
    protected LocalDate dataAbertura;
    protected LocalDate dataCancelamento;
    protected String justificativaCancelamento;
    protected MotivoCancelamento motivoCancelamento;
    protected ArrayList<Operacao> historicoOperacoes;

    public Cliente getCliente() {
        return cliente;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public Integer getNumeroAgencia() {
        return numeroAgencia;
    }

    public Double getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public Boolean getContaCancelada() {
        return contaCancelada;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public LocalDate getDataCancelamento() {
        return dataCancelamento;
    }

    public String getJustificativaCancelamento() {
        return justificativaCancelamento;
    }

    public MotivoCancelamento getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public ArrayList<Operacao> getHistoricoOperacoes() {
        return historicoOperacoes;
    }

    protected static void validarConta(ContaBancaria conta) {
        if (conta == null || conta.contaCancelada)
            throw new ContaInvalidaException("Uma ContaBancaria ativa e necessaria: " + conta);
    }

    protected static void validarConta(ContaBancaria primeiraConta, ContaBancaria segundaConta) {
        ContaBancaria.validarConta(primeiraConta);
        ContaBancaria.validarConta(segundaConta);

        if (primeiraConta.numeroConta == segundaConta.numeroConta) {
            StringBuilder mensagem = new StringBuilder();
            mensagem.append("As contas precisam ser diferentes. ");
            mensagem.append("primeiraConta: ").append(primeiraConta.numeroConta).append(" ");
            mensagem.append("segundaConta: ").append(segundaConta.numeroConta);
            throw new ContaInvalidaException(mensagem.toString());
        }
    }

    protected static void validarValor(Double valor) {
        if (valor == null || valor <= 0) throw new SaldoInvalidoException("Um valor positivo e necessario: " + valor);
    }

    protected static void validarSaldo(ContaBancaria conta, Double valor) {
        ContaBancaria.validarConta(conta);
        ContaBancaria.validarValor(valor);

        if (valor > conta.saldoDisponivel) {
            StringBuilder mensagem = new StringBuilder();
            mensagem.append("O valor precisa ser inferior ao saldo disponivel. ");
            mensagem.append("Saldo: ").append(conta.saldoDisponivel).append(" ");
            mensagem.append("Valor: ").append(valor);
            throw new SaldoInvalidoException(mensagem.toString());
        }
    }

    protected static void validarData(LocalDate dataInicial, LocalDate dataFinal) {
        if (dataInicial == null || dataFinal == null || dataInicial.isAfter(dataFinal)) {
            StringBuilder mensagem = new StringBuilder();
            mensagem.append("A dataInicial precisa ser anterior a final. ");
            mensagem.append("dataInicial: ").append(dataInicial).append(" ");
            mensagem.append("dataFinal: ").append(dataFinal);
            throw new DataInvalidaException(mensagem.toString());
        }
    }

    protected static void validarJustificativa(String justificativa) {
        if (justificativa == null || justificativa.isBlank())
            throw new JustificativaInvalidaException("A justificativa precisa ser preenchida: " + justificativa);
    }

    protected void adicionarHistorico(TipoOperacao tipo, String descricao, Double valor) {
        LocalDate data = LocalDate.now();
        Operacao operacao = new Operacao(data, tipo, descricao, valor);
        this.historicoOperacoes.add(operacao);
    }
}
