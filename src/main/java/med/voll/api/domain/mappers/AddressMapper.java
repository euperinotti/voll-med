package med.voll.api.domain.mappers;

import med.voll.api.application.dto.AddressDTO;
import med.voll.api.domain.entities.AddressBO;

public class AddressMapper {
  public static AddressBO toBO(AddressDTO dto) {
    AddressBO bo = new AddressBO(dto.street(), dto.neighborhood(), dto.zipCode(), dto.number(), dto.complement(),
        dto.city(), dto.state());

    return bo;
  }
}
