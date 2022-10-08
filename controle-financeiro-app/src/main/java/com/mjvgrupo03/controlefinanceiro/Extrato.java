package com.mjvgrupo03.controlefinanceiro;

import java.time.LocalDate;

public class Extrato
{
    LocalDate data;
    String transacao;
    Double valor;

    public Extrato(LocalDate data, String transacao, Double valor)
    {
        this.data = data;
        this.transacao = transacao;
        this.valor = valor;
    }

    @Override
    public String toString()
    {
        StringBuilder representacao = new StringBuilder();
        representacao.append("Data: " + this.data.toString() + "\n");
        representacao.append("Transacao: " + this.transacao + "\n");
        representacao.append("Valor: " + this.valor + "\n");
        return representacao.toString();
    }
}
