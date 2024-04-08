package med.voll.api.application.dto;

import med.voll.api.domain.entities.AddressBO;
import med.voll.api.domain.entities.Pacient;

public record PacientDetailsDTO(Long id, String name, String email, String phone, String cpf, AddressBO address, Boolean isActive) {

  public PacientDetailsDTO(Pacient pacient) {
    this(pacient.getId(), pacient.getName(), pacient.getEmail(), pacient.getPhone(), pacient.getCpf(), pacient.getAddress(), pacient.getIsActive());
  }
  
}
