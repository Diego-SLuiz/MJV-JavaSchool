package conta;

public enum TipoOperacao
{
    CREDITO('C'),
    DEBITO('D'),
    TRANSFERENCIA('T');

    private final char sigla;

    public char getSigla() { return sigla; }

    TipoOperacao(char sigla)
    {
        this.sigla = sigla;
    }
}
