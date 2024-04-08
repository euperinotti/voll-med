package med.voll.api.domain.usecases;

import med.voll.api.application.dto.ScheduleAppointmentDTO;

public interface IScheduleAppointmentUseCase {
  public void execute(ScheduleAppointmentDTO dto);
}
