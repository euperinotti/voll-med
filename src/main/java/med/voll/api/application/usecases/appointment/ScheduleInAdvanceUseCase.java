package med.voll.api.application.usecases.appointment;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.application.dto.ScheduleAppointmentDTO;
import med.voll.api.domain.usecases.IScheduleAppointmentUseCase;

@Component
public class ScheduleInAdvanceUseCase implements IScheduleAppointmentUseCase {
  public void execute(ScheduleAppointmentDTO dto) {
    LocalDateTime appointmentDate = dto.date();
    LocalDateTime now = LocalDateTime.now();
    Long differenceInMinutes = Duration.between(appointmentDate, now).toMinutes();
    Integer scheduleInterval = 30;

    if (differenceInMinutes < scheduleInterval) {
      throw new RuntimeException("Consulta deve ser agendada com antecedência minima de 30 minutos");
    }
  }
}
