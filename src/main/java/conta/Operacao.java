package conta;

import conta.enumeration.StatusOperacao;
import conta.enumeration.TipoOperacao;

import java.time.LocalDate;

public class Operacao
{
    private LocalDate data;
    private String descricao;
    private Double valor;
    private StatusOperacao status;
    private TipoOperacao tipo;

    public Operacao(String descricao, Double valor, TipoOperacao tipo) {
        this.data = LocalDate.now();
        this.descricao = descricao;
        this.valor = valor;
        this.status = StatusOperacao.PENDENTE;
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }

    public StatusOperacao getStatus() {
        return status;
    }

    public TipoOperacao getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Operacao{" +
                "data=" + data +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", status=" + status +
                ", tipo=" + tipo +
                '}';
    }

    public void concluir() {
        this.status = StatusOperacao.CONCLUIDA;
    }

    public void cancelar() {
        this.status = StatusOperacao.ESTORNADA;
    }
}
