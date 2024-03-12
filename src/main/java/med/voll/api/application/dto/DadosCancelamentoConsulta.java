package med.voll.api.application.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consulta.EnumMotivoCancelamento;

public record DadosCancelamentoConsulta(
    @NotNull Long idConsulta,

    @NotNull EnumMotivoCancelamento motivo) {
}
