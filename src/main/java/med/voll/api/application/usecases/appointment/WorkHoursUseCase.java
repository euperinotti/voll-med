package med.voll.api.application.usecases.appointment;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.application.dto.ScheduleAppointmentDTO;

@Component
public class WorkHoursUseCase implements IScheduleAppointmentUseCase{
  public void execute(ScheduleAppointmentDTO dto) {
    LocalDateTime appointmentDate = dto.date();

    boolean sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    boolean beforeSevenAM = appointmentDate.getHour() < 7;
    boolean afterSevenPM = appointmentDate.getHour() > 18;

    if (sunday || beforeSevenAM || afterSevenPM) {
      throw new RuntimeException("Consulta fora do horário de agendamento");
    }
  }
}
