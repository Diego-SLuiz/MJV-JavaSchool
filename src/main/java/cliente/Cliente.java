package cliente;

import cliente.enumeration.TipoVinculo;
import cliente.exception.ContaExistenteException;
import conta.ContaBancaria;

public class Cliente {
    private String nome;
    private ContaBancaria conta;
    private TipoVinculo vinculo;
    private String identificacao;

    public Cliente(String nome, TipoVinculo vinculo, String identificacao) {
        this.nome = nome;
        this.conta = null;
        this.vinculo = vinculo;
        this.identificacao = identificacao;
    }

    public String getNome() {
        return nome;
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta(ContaBancaria conta) {
        if (this.conta != null)
            throw new ContaExistenteException("Ja existe uma conta atribuida ao cliente");

        this.conta = conta;
    }

    public TipoVinculo getVinculo() {
        return vinculo;
    }

    public String getIdentificacao() {
        return identificacao;
    }
}
