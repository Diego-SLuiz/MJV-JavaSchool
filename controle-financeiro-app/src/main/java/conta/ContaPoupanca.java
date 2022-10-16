package conta;

import cliente.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContaPoupanca extends ContaBancaria {
    private Double chequeEspecial;

    public ContaPoupanca(Cliente cliente, Integer numeroConta, Integer numeroAgencia) {
        this.cliente = cliente;
        this.numeroConta = numeroConta;
        this.numeroAgencia = numeroAgencia;
        this.dataAbertura = LocalDate.now();
        this.saldoDisponivel = 0.0;
        this.chequeEspecial = 0.0;
        this.contaCancelada = false;
        this.historicoOperacoes = new ArrayList<Operacao>();
    }
}
