package conta;

import cliente.Cliente;
import conta.enumeration.TipoOperacao;
import conta.enumeration.MotivoCancelamento;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContaCorrente extends ContaBancaria {
    private Double jurosAnual;

    public ContaCorrente(Cliente cliente, Integer numeroConta, Integer numeroAgencia) {
        this.cliente = cliente;
        this.numeroConta = numeroConta;
        this.numeroAgencia = numeroAgencia;
        this.dataAbertura = LocalDate.now();
        this.saldoDisponivel = 0.0;
        this.jurosAnual = 0.0644;
        this.contaCancelada = false;
        this.historicoOperacoes = new ArrayList<Operacao>();
    }

    public void sacar(Double valor) {
        ContaCorrente.validarConta(this);
        ContaCorrente.validarValor(valor);
        ContaCorrente.validarSaldo(this, valor);

        this.saldoDisponivel -= valor;
        this.adicionarHistorico(TipoOperacao.DEBITO, "Retirando valor da conta", valor);
    }

    public void depositar(Double valor) {
        ContaCorrente.validarConta(this);
        ContaCorrente.validarValor(valor);

        this.saldoDisponivel += valor;
        this.adicionarHistorico(TipoOperacao.CREDITO, "Depositando valor na conta", valor);
    }

    public void transferir(Double valor, ContaCorrente destinatario) {
        ContaCorrente.validarConta(this, destinatario);
        ContaCorrente.validarValor(valor);
        ContaCorrente.validarSaldo(this, valor);

        this.saldoDisponivel -= valor;
        this.adicionarHistorico(TipoOperacao.TRANSFERENCIA, "Transferindo saldo para " + destinatario.cliente.getNome(), valor);
        destinatario.saldoDisponivel += valor;
        destinatario.adicionarHistorico(TipoOperacao.TRANSFERENCIA, "Recebendo saldo de " + this.cliente.getNome(), valor);
    }

    public void cancelarConta(String justificativa, MotivoCancelamento motivo) {
        ContaCorrente.validarConta(this);
        ContaCorrente.validarJustificativa(justificativa);

        this.contaCancelada = true;
        this.motivoCancelamento = motivo;
        this.dataCancelamento = LocalDate.now();
    }

    public ArrayList<Operacao> consultarExtrato(LocalDate dataInicial, LocalDate dataFinal) {
        ContaCorrente.validarConta(this);
        ContaCorrente.validarData(dataInicial, dataFinal);

        ArrayList<Operacao> extratoOperacoes = new ArrayList<Operacao>();

        for (Operacao x : this.historicoOperacoes)
            if ((x.getData().isAfter(dataInicial) || x.getData().isEqual(dataInicial)) && x.getData().isBefore(dataFinal))
                extratoOperacoes.add(x);
        return extratoOperacoes;
    }
}
