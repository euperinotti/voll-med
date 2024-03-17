package med.voll.api.application.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.appointment.EnumCancelReason;

public record CancelAppointmentDTO(
    @NotNull Long appointmentId,

    @NotNull EnumCancelReason reason) {
}
