package med.voll.api.application.usecases.appointment;

import med.voll.api.application.dto.ScheduleAppointmentDTO;

public interface IScheduleAppointmentUseCase {
  public void execute(ScheduleAppointmentDTO dto);
}
