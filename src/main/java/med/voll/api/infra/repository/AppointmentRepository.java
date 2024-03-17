package med.voll.api.infra.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.appointment.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

  boolean existsByDoctorIdAndDate(Long doctorId, @NotNull @Future LocalDateTime date);

  boolean existsByDoctorIdAndDateAndCancelReasonIsNull(Long doctorId, LocalDateTime date);

  boolean existsPacientByIdAndDateBetween(@NotNull Long pacientId, LocalDateTime firstTime, LocalDateTime lastTime);
  
}
