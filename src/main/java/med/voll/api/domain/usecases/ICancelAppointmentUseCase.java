package med.voll.api.domain.usecases;

import med.voll.api.application.dto.CancelAppointmentDTO;

public interface ICancelAppointmentUseCase {
  void execute(CancelAppointmentDTO data);
}
