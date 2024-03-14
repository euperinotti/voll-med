package med.voll.api.application.usecases.consulta;

import java.time.LocalDateTime;

import med.voll.api.application.dto.DadosAgendamentoConsulta;
import med.voll.api.infra.repository.ConsultaRepository;

public class PacientWithOtherAppointmentsSameDay implements IConsultaUseCase {

  private ConsultaRepository repository;

  @Override
  public void execute(DadosAgendamentoConsulta dados) {
    LocalDateTime firstTime = dados.data().withHour(7);
    LocalDateTime lastTime = dados.data().withHour(18);

    boolean hasOtherAppointmentsOnSameDay =  repository.existsPacienteByIdAndDataBetween(dados.idPaciente(), firstTime, lastTime);

    if(hasOtherAppointmentsOnSameDay) {
      throw new RuntimeException("Já existe uma consulta no mesmo dia");
    }

  }

  
}
