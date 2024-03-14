package med.voll.api.application.usecases.consulta;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import med.voll.api.application.dto.DadosAgendamentoConsulta;

public class HorarioFuncionamentoUseCase {
  public void execute(DadosAgendamentoConsulta dados) {
    LocalDateTime dataConsulta = dados.data();

    boolean sunday = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    boolean beforeSevenAM = dataConsulta.getHour() < 7;
    boolean afterSevenPM = dataConsulta.getHour() > 18;

    if (sunday || beforeSevenAM || afterSevenPM) {
      throw new RuntimeException("Consulta fora do horário de agendamento");
    }
  }
}
