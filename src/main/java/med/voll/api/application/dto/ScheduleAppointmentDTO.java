package med.voll.api.application.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Specialty;

public record ScheduleAppointmentDTO(
    Long doctorId,

    @NotNull Long pacientId,

    @NotNull @Future LocalDateTime date,
    
    Specialty specialty
    ) {

}
