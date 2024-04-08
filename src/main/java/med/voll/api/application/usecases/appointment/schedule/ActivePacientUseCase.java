package med.voll.api.application.usecases.appointment.schedule;

import org.springframework.stereotype.Component;

import med.voll.api.application.dto.ScheduleAppointmentDTO;
import med.voll.api.domain.usecases.IScheduleAppointmentUseCase;
import med.voll.api.infra.jpa.repository.PacientRepository;

@Component
public class ActivePacientUseCase implements IScheduleAppointmentUseCase {

  private PacientRepository repository;

  @Override
  public void execute(ScheduleAppointmentDTO dados) {
    boolean pacientIsActive = repository.findActiveById(dados.pacientId());

    if(!pacientIsActive) {
      throw new RuntimeException("Paciente não está ativo");
    }
  }
  
}
