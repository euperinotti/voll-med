package med.voll.api.application.usecases.appointment;

import org.springframework.stereotype.Component;

import med.voll.api.application.dto.ScheduleAppointmentDTO;
import med.voll.api.infra.repository.PacientRepository;

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
