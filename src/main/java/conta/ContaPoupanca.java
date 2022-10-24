package conta;

import conta.enumeration.StatusConta;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContaPoupanca extends ContaBancaria {
    private Double jurosAnual;

    public ContaPoupanca(Integer numeroConta, Integer numeroAgencia) {
        this.dataAbertura = LocalDate.now();
        this.numeroConta = numeroConta;
        this.numeroAgencia = numeroAgencia;
        this.statusConta = StatusConta.ATIVA;
        this.historicoOperacoes = new ArrayList<Operacao>();
        this.cancelamento = null;
        this.saldoDisponivel = 0.0;
        this.jurosAnual = 0.0617;
    }
}
