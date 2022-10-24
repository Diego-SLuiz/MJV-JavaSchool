package conta;

import conta.enumeration.MotivoCancelamento;
import conta.enumeration.StatusConta;
import conta.enumeration.TipoOperacao;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContaCorrente extends ContaBancaria {
    private Double chequeEspecial;

    public ContaCorrente(Integer numeroConta, Integer numeroAgencia) {
        this.dataAbertura = LocalDate.now();
        this.numeroConta = numeroConta;
        this.numeroAgencia = numeroAgencia;
        this.statusConta = StatusConta.ATIVA;
        this.historicoOperacoes = new ArrayList<Operacao>();
        this.cancelamento = null;
        this.saldoDisponivel = 0.0;
        this.chequeEspecial = 0.0;
    }

    public void sacar(Double valor) {
        ContaCorrente.validarConta(this);
        ContaCorrente.validarValor(valor);
        ContaCorrente.validarSaldo(this, valor);

        this.saldoDisponivel -= valor;
        this.adicionarHistorico("Sacando valor da conta", valor, TipoOperacao.DEBITO);
    }

    public void depositar(Double valor) {
        ContaCorrente.validarConta(this);
        ContaCorrente.validarValor(valor);

        this.saldoDisponivel += valor;
        this.adicionarHistorico("Depositando valor na conta", valor, TipoOperacao.CREDITO);
    }

    public void transferir(Double valor, ContaBancaria destinatario) {
        ContaCorrente.validarConta(this, destinatario);
        ContaCorrente.validarValor(valor);
        ContaCorrente.validarSaldo(this, valor);

        this.saldoDisponivel -= valor;
        this.adicionarHistorico("Transferindo valor da conta", valor, TipoOperacao.TRANSFERENCIA);
        destinatario.saldoDisponivel += valor;
        destinatario.adicionarHistorico("Recebendo transferencia de valor", valor, TipoOperacao.TRANSFERENCIA);
    }

    public void cancelarConta(String justificativa, MotivoCancelamento motivo) {
        ContaCorrente.validarConta(this);

        this.statusConta = StatusConta.CANCELADA;
        this.cancelamento = new Cancelamento(justificativa, motivo);
    }

    public ArrayList<Operacao> consultarExtrato(LocalDate dataInicial, LocalDate dataFinal) {
        ContaCorrente.validarConta(this);
        ContaCorrente.validarData(dataInicial, dataFinal);

        ArrayList<Operacao> extratoOperacoes = new ArrayList<Operacao>();

        for (Operacao x: this.historicoOperacoes)
            if ((x.getData().isAfter(dataInicial) || x.getData().isEqual(dataInicial)) && x.getData().isBefore(dataFinal))
                extratoOperacoes.add(x);
        return extratoOperacoes;
    }
}
