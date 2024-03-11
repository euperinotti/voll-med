package med.voll.api.application.dto;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
    Long id,
    Long idMedico,
    Long idPaciente,
    LocalDateTime data) {

}
