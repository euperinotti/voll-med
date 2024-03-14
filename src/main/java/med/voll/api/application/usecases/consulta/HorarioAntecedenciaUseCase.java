package med.voll.api.application.usecases.consulta;

import java.time.Duration;
import java.time.LocalDateTime;

import med.voll.api.application.dto.DadosAgendamentoConsulta;

public class HorarioAntecedenciaUseCase {
  public void execute(DadosAgendamentoConsulta dados) {
    LocalDateTime dataConsulta = dados.data();
    LocalDateTime now = LocalDateTime.now();
    Long differenceInMinutes = Duration.between(dataConsulta, now).toMinutes();
    Integer scheduleInterval = 30;

    if (differenceInMinutes < scheduleInterval) {
      throw new RuntimeException("Consulta deve ser agendada com antecedência minima de 30 minutos");
    }
  }
}
