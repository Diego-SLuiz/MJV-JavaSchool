package conta;

import java.time.LocalDate;

public class Operacao
{
    LocalDate data;
    TipoOperacao tipo;
    String descricao;
    Double valor;

    public Operacao(LocalDate data, TipoOperacao tipo, String descricao, Double valor)
    {
        this.data = data;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
    }
}
