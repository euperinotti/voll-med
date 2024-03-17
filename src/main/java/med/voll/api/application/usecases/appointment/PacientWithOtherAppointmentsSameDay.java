package med.voll.api.application.usecases.appointment;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.application.dto.ScheduleAppointmentDTO;
import med.voll.api.infra.repository.AppointmentRepository;

@Component
public class PacientWithOtherAppointmentsSameDay implements IScheduleAppointmentUseCase {

  @Autowired
  private AppointmentRepository repository;

  @Override
  public void execute(ScheduleAppointmentDTO dto) {
    LocalDateTime firstTime = dto.date().withHour(7);
    LocalDateTime lastTime = dto.date().withHour(18);

    boolean hasOtherAppointmentsOnSameDay =  repository.existsPacientByIdAndDateBetween(dto.pacientId(), firstTime, lastTime);

    if(hasOtherAppointmentsOnSameDay) {
      throw new RuntimeException("Já existe uma consulta no mesmo dia");
    }

  }

  
}
