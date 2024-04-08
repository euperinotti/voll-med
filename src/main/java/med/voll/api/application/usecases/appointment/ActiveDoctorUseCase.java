package med.voll.api.application.usecases.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.application.dto.ScheduleAppointmentDTO;
import med.voll.api.infra.jpa.repository.DoctorRepository;

@Component
public class ActiveDoctorUseCase implements IScheduleAppointmentUseCase { 

  @Autowired
  private DoctorRepository repository;

  @Override
  public void execute(ScheduleAppointmentDTO dto) {
    if (dto.doctorId() == null) {
      return;
    }

    boolean isActive = repository.findActiveById(dto.doctorId());

    if(!isActive) {
      throw new RuntimeException("Médico inativo");
    }
  }
}
