package conta;

import conta.enumeration.TipoOperacao;

import java.time.LocalDate;

public class Operacao
{
    private LocalDate data;
    private TipoOperacao tipo;
    private String descricao;
    private Double valor;

    public Operacao(LocalDate data, TipoOperacao tipo, String descricao, Double valor)
    {
        this.data = data;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public LocalDate getData() { return data; }

    public TipoOperacao getTipo() { return tipo; }

    public String getDescricao() { return descricao; }

    public Double getValor() { return valor; }

    @Override
    public String toString() {
        StringBuilder representacao = new StringBuilder();
        representacao.append("Data: " + this.data + "\n");
        representacao.append("Tipo: " + this.tipo + "\n");
        representacao.append("Descricao: " + this.descricao + "\n");
        representacao.append("Valor: " + this.valor + "\n");
        return representacao.toString();
    }
}
