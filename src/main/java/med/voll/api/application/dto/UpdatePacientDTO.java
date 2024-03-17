package med.voll.api.application.dto;

import jakarta.validation.constraints.NotNull;

public record UpdatePacientDTO(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDTO address) {
}
