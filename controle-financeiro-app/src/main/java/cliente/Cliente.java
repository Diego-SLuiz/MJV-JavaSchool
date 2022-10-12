package cliente;

import java.time.LocalDate;

public class Cliente
{
    private String nome;
    private LocalDate nascimento;
    private String cpf;

    public Cliente(String nome, LocalDate nascimento, String cpf)
    {
        this.nome = nome;
        this.nascimento = nascimento;
        this.cpf = cpf;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }


    public LocalDate getNascimento()
    {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento)
    {
        this.nascimento = nascimento;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }
}
