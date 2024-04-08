package med.voll.api.application.usecases.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.application.dto.ScheduleAppointmentDTO;
import med.voll.api.infra.jpa.repository.AppointmentRepository;

@Component
public class DoctorNotAvailableAtDate implements IScheduleAppointmentUseCase {

  @Autowired
  private AppointmentRepository repository;

  @Override
  public void execute(ScheduleAppointmentDTO dto) {
    boolean hasOtherAppointmentSchedule = repository.existsByDoctorIdAndDateAndCancelReasonIsNull(dto.doctorId(), dto.date());

    if (hasOtherAppointmentSchedule) { 
      throw new RuntimeException("Já existe uma consulta no mesmo horário");
    }
  }
  
}
