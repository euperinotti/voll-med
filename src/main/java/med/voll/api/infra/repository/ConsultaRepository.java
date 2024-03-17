package med.voll.api.infra.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consulta.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

  boolean existsByMedicoIdAndData(Long idMedico, @NotNull @Future LocalDateTime data);

  boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime data);

  boolean existsPacienteByIdAndDataBetween(@NotNull Long idPaciente, LocalDateTime firstTime, LocalDateTime lastTime);
  
}
