package conta;

import conta.enumeration.MotivoCancelamento;
import conta.exception.JustificativaInvalidaException;

import java.time.LocalDate;

public class Cancelamento {
    private LocalDate data;
    private String justificativa;
    private MotivoCancelamento motivo;

    public Cancelamento(String justificativa, MotivoCancelamento motivo) {
        Cancelamento.validarJustificativa(justificativa);
        this.data = LocalDate.now();
        this.justificativa = justificativa;
        this.motivo = motivo;
    }

    private static void validarJustificativa(String justificativa) {
        if (justificativa == null || justificativa.isBlank()) {
            throw new JustificativaInvalidaException(String.format("A justificativa precisa ser preenchida: %s\n", justificativa));
        }
    }

    public LocalDate getData() {
        return data;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public MotivoCancelamento getMotivo() {
        return motivo;
    }
}
