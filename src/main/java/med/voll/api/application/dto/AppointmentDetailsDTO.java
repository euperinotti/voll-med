package med.voll.api.application.dto;

import java.time.LocalDateTime;

import med.voll.api.domain.appointment.Appointment;

public record AppointmentDetailsDTO(
    Long id,
    Long doctorId,
    Long pacientId,
    LocalDateTime date) {

  public AppointmentDetailsDTO(Appointment appointment) {
    this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPacient().getId(), appointment.getDate());
  }

}
