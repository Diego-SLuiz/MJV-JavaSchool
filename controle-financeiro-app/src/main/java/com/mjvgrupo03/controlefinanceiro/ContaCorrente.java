package com.mjvgrupo03.controlefinanceiro;

import com.mjvgrupo03.controlefinanceiro.exception.ContaInvalidaException;
import com.mjvgrupo03.controlefinanceiro.exception.DataInvalidaException;
import com.mjvgrupo03.controlefinanceiro.exception.JustificativaInvalidaException;
import com.mjvgrupo03.controlefinanceiro.exception.SaldoInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContaCorrente
{
    private String nomeCliente;
    private Integer numeroConta;
    private Integer numeroAgencia;
    private Double saldoDisponivel;
    private Boolean contaCancelada;
    private ArrayList<Extrato> listaExtratos;

    public ContaCorrente(String nomeCliente, Integer numeroConta, Integer numeroAgencia)
    {
        this.nomeCliente = nomeCliente;
        this.numeroConta = numeroConta;
        this.numeroAgencia = numeroAgencia;
        this.saldoDisponivel = 0.0;
        this.contaCancelada = false;
        this.listaExtratos = new ArrayList<Extrato>();
    }

    public ArrayList<Extrato> getListaExtratos() { return listaExtratos; }

    public String getNomeCliente()
    {
        return nomeCliente;
    }

    public Double getSaldoDisponivel()
    {
        return saldoDisponivel;
    }

    public Boolean getContaCancelada()
    {
        return contaCancelada;
    }

    public boolean sacar(Double valor) throws SaldoInvalidoException
    {
        // Validar se o saldo é suficiente e exibir erro
        if (valor > this.saldoDisponivel)
            throw new SaldoInvalidoException("O valor de saque precisa ser inferior ao saldo");
        if (valor <= 0)
            throw new SaldoInvalidoException("O valor de saque precisa ser superior a zero");

        // Efetuar o saque e exibir mensagem
        System.out.println("Saque efetuado com sucesso da conta de " + this.nomeCliente);
        System.out.println("Valor: " + valor + "\n");
        this.saldoDisponivel -= valor;

        // Adicionar transação para o histórico
        LocalDate dataExtrato = LocalDate.now();
        Extrato extratoGerado = new Extrato(dataExtrato, "Saque", valor);
        this.listaExtratos.add(extratoGerado);

        return true;
    }

    public boolean depositar(Double valor) throws SaldoInvalidoException
    {
        // Validar o valor de depósito e exibir erro
        if (valor <= 0)
            throw new SaldoInvalidoException("O valor de deposito precisar ser superior a zero");

        // Efetuar o depósito e exibir mensagem
        System.out.println("O deposito foi efetuado com sucesso na conta de " + this.nomeCliente);
        System.out.println("Valor: " + valor + "\n");
        this.saldoDisponivel += valor;

        // Adicionar transação para o histórico
        LocalDate dataExtrato = LocalDate.now();
        Extrato extratoGerado = new Extrato(dataExtrato, "Deposito", valor);
        this.listaExtratos.add(extratoGerado);

        return true;
    }

    public boolean transferir(Double valor, ContaCorrente destinatario) throws SaldoInvalidoException, ContaInvalidaException
    {
        // Validar se a transferência é válida e exibir erro
        if (valor <= 0)
            throw new SaldoInvalidoException("O valor de transferencia precisa ser superior a zero");
        if (valor > this.saldoDisponivel)
            throw new SaldoInvalidoException("O valor de transferencia precisa ser inferior ao saldo");
        if (destinatario == null)
            throw new ContaInvalidaException("A conta de destino nao pode ser nula");

        // Efetuar a transferência e exibir mensagem
        System.out.println("Tranferencia realizada com sucesso");
        System.out.println("De: " + this.nomeCliente);
        System.out.println("Para: " + destinatario.nomeCliente);
        System.out.println("Valor: " + valor + "\n");
        this.saldoDisponivel -= valor;
        destinatario.saldoDisponivel += valor;

        // Adicionar transação para o histórico
        LocalDate dataExtrato = LocalDate.now();
        Extrato extratoGerado = new Extrato(dataExtrato, "Transferencia", valor);
        this.listaExtratos.add(extratoGerado);

        return true;
    }

    public boolean cancelarConta(String justificativa) throws JustificativaInvalidaException
    {
        // Verificar a justificativa e exibir erro
        if (justificativa.isBlank())
            throw new JustificativaInvalidaException("A justificativa nao pode ser vazia");

        // Cancelar conta e exibir mensagem
        System.out.println("Conta cancelada com sucesso");
        System.out.println("Nome: " + this.nomeCliente);
        System.out.println("Justificativa: " + justificativa + "\n");
        this.contaCancelada = true;

        return true;
    }

    public boolean consultarExtrato(LocalDate dataInicial, LocalDate dataFinal) throws DataInvalidaException
    {
        // Verificar se a data inicial é posterior a final e exibir erro
        if (dataInicial.isAfter(dataFinal))
            throw new DataInvalidaException("A data inicial nao pode ser posterior a final");

        // Consultar extrato entre as datas e exibir mensagem
        System.out.println("Consultando extratos: ");
        System.out.println("De: " + dataInicial.toString());
        System.out.println("Ate: " + dataFinal.toString() + "\n");

        for (Extrato x: this.listaExtratos)
            if ((x.data.isAfter(dataInicial) || x.data.isEqual(dataInicial)) && x.data.isBefore(dataFinal))
                System.out.println(x);

        return true;
    }
}
