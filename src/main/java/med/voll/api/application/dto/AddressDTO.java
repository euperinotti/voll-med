package med.voll.api.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
    @NotBlank String street,
    @NotBlank String neighborhood,
    @NotBlank @Pattern(regexp = "\\d{8}") String zipCode,
    @NotBlank String city,
    @NotBlank String state,
    String complement,
    String number) {
}
