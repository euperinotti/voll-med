package med.voll.api.application.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateDoctorDTO(
    @NotNull Long id,
    String name,
    String phone,
    AddressDTO address) {
}
