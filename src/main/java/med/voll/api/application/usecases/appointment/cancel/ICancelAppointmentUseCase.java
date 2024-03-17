package med.voll.api.application.usecases.appointment.cancel;

import med.voll.api.application.dto.CancelAppointmentDTO;

public interface ICancelAppointmentUseCase {
  void execute(CancelAppointmentDTO data);
}
