package med.voll.api.application.usecases.appointment.cancel;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.application.dto.CancelAppointmentDTO;
import med.voll.api.domain.entities.Appointment;
import med.voll.api.infra.repository.AppointmentRepository;

@Component
public class CancelInAdvanceUseCase implements ICancelAppointmentUseCase {

  @Autowired
  private AppointmentRepository repository;

  @Override
  public void execute(CancelAppointmentDTO data) {
    Appointment appointment = repository.getReferenceById(data.appointmentId());
    LocalDateTime now = LocalDateTime.now();
    Long differenceInHours = Duration.between(now, appointment.getDate()).toHours();

    if (differenceInHours < 24) {
      throw new RuntimeException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
    }
  }

}
