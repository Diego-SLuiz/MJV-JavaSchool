package conta;

public enum TipoOperacao
{
    CREDITO('C'),
    DEBITO('D'),
    TRANSFERENCIA('T');

    final char sigla;

    TipoOperacao(char sigla)
    {
        this.sigla = sigla;
    }
}
