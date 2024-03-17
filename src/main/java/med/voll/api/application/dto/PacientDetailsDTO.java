package med.voll.api.application.dto;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.pacient.Pacient;

public record PacientDetailsDTO(Long id, String name, String email, String phone, String cpf, Address address, Boolean isActive) {

  public PacientDetailsDTO(Pacient pacient) {
    this(pacient.getId(), pacient.getName(), pacient.getEmail(), pacient.getPhone(), pacient.getCpf(), pacient.getAddress(), pacient.getIsActive());
  }
  
}
