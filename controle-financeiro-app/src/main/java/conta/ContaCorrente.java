package conta;

import cliente.Cliente;

import conta.exception.ContaInvalidaException;
import conta.exception.DataInvalidaException;
import conta.exception.JustificativaInvalidaException;
import conta.exception.SaldoInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContaCorrente
{
    private Cliente cliente;
    private Integer numeroConta;
    private Integer numeroAgencia;
    private Double saldoDisponivel;
    private Boolean contaCancelada;
    private ArrayList<Operacao> historicoOperacoes;

    public ContaCorrente(Cliente cliente, Integer numeroConta, Integer numeroAgencia)
    {
        this.cliente = cliente;
        this.numeroConta = numeroConta;
        this.numeroAgencia = numeroAgencia;
        this.saldoDisponivel = 0.0;
        this.contaCancelada = false;
        this.historicoOperacoes = new ArrayList<Operacao>();
    }

    public Cliente getCliente() { return this.cliente; }

    public Double getSaldoDisponivel() { return this.saldoDisponivel; }

    public Boolean getContaCancelada() { return this.contaCancelada; }

    public ArrayList<Operacao> getHistoricoOperacoes() { return this.historicoOperacoes; }

    private static void validarConta(ContaCorrente conta)
    {
        if (conta == null || conta.contaCancelada)
            throw new ContaInvalidaException("Uma ContaCorrente nao nula e nao cancelada e necessaria");
    }

    private static void validarConta(ContaCorrente primeiraConta, ContaCorrente segundaConta)
    {
        ContaCorrente.validarConta(primeiraConta);
        ContaCorrente.validarConta(segundaConta);

        if (primeiraConta.numeroConta == segundaConta.numeroConta)
            throw new ContaInvalidaException("As contas precisam ser diferentes");
    }

    private static void validarValor(Double valor)
    {
        if (valor == null || valor <= 0)
            throw new SaldoInvalidoException("Um Double nao nulo e maior que zero e necessario");
    }

    private static void validarSaldo(ContaCorrente conta, Double valor)
    {
        ContaCorrente.validarConta(conta);
        ContaCorrente.validarValor(valor);

        if (valor > conta.saldoDisponivel)
            throw new SaldoInvalidoException("O valor nao pode ser maior que o saldo disponivel");
    }

    private static void validarData(LocalDate dataInicial, LocalDate dataFinal)
    {
        if (dataInicial == null || dataFinal == null || dataInicial.isAfter(dataFinal))
            throw new DataInvalidaException("A dataInicial e dataFinal nao podem ser nulas e a dataInicial precisa ser anterior a final");
    }

    private static void validarJustificativa(String justificativa)
    {
        if (justificativa == null || justificativa.isBlank())
            throw  new JustificativaInvalidaException("A justificativa precisa ser uma String nao nula e nao vazia");
    }

    private void adicionarHistorico(TipoOperacao tipo, String descricao, Double valor)
    {
        LocalDate data = LocalDate.now();
        Operacao operacao = new Operacao(data, tipo, descricao, valor);
        this.historicoOperacoes.add(operacao);
    }

    public void sacar(Double valor)
    {
        // Validação dos dados
        ContaCorrente.validarConta(this);
        ContaCorrente.validarValor(valor);
        ContaCorrente.validarSaldo(this, valor);

        // Efetuar o saque e exibir mensagem
        System.out.println("Saque efetuado com sucesso da conta de " + this.cliente.getNome());
        System.out.println("Valor: " + valor + "\n");

        this.saldoDisponivel -= valor;

        // Adicionar operação para o histórico de operações
        this.adicionarHistorico(TipoOperacao.DEBITO, "Retirando valor da conta", valor);
    }

    public void depositar(Double valor)
    {
        // Validação dos dados
        ContaCorrente.validarConta(this);
        ContaCorrente.validarValor(valor);

        // Efetuar o depósito e exibir mensagem
        System.out.println("O deposito foi efetuado com sucesso na conta de " + this.cliente.getNome());
        System.out.println("Valor: " + valor + "\n");

        this.saldoDisponivel += valor;

        // Adicionar operação para o histórico de operações
        this.adicionarHistorico(TipoOperacao.CREDITO, "Depositando valor na conta", valor);
    }

    public void transferir(Double valor, ContaCorrente destinatario)
    {
        // Validação dos dados
        ContaCorrente.validarConta(this, destinatario);
        ContaCorrente.validarValor(valor);
        ContaCorrente.validarSaldo(this, valor);

        // Efetuar a transferência e exibir mensagem
        System.out.println("Tranferencia realizada com sucesso");
        System.out.println("De: " + this.cliente.getNome());
        System.out.println("Para: " + destinatario.cliente.getNome());
        System.out.println("Valor: " + valor + "\n");

        this.saldoDisponivel -= valor;
        destinatario.saldoDisponivel += valor;

        // Adicionar operação para o histórico de operações
        this.adicionarHistorico(TipoOperacao.TRANSFERENCIA, "Transferindo saldo para " + destinatario.cliente.getNome(), valor);
        destinatario.adicionarHistorico(TipoOperacao.TRANSFERENCIA, "Recebendo saldo de " + this.cliente.getNome(), valor);
    }

    public void cancelarConta(String justificativa)
    {
        // Validação dos dados
        ContaCorrente.validarConta(this);
        ContaCorrente.validarJustificativa(justificativa);

        // Cancelar conta e exibir mensagem
        System.out.println("Conta cancelada com sucesso");
        System.out.println("Nome: " + this.cliente.getNome());
        System.out.println("Justificativa: " + justificativa + "\n");

        this.contaCancelada = true;
    }

    public ArrayList<Operacao> consultarExtrato(LocalDate dataInicial, LocalDate dataFinal)
    {
        // Validação dos dados
        ContaCorrente.validarData(dataInicial, dataFinal);

        // Consultar extrato entre as datas e exibir mensagem
        System.out.println("Consultando extrato: ");
        System.out.println("Nome: " + this.cliente.getNome());
        System.out.println("De: " + dataInicial.toString());
        System.out.println("Ate: " + dataFinal.toString() + "\n");

        ArrayList<Operacao> extratoOperacoes = new ArrayList<Operacao>();

        for (Operacao x: this.historicoOperacoes)
            if ((x.getData().isAfter(dataInicial) || x.getData().isEqual(dataInicial)) && x.getData().isBefore(dataFinal))
                extratoOperacoes.add(x);
        return extratoOperacoes;
    }
}
