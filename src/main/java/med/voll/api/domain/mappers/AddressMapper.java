package med.voll.api.domain.mappers;

import med.voll.api.application.dto.AddressDTO;
import med.voll.api.domain.entities.AddressBO;
import med.voll.api.infra.jakarta.repository.models.Address;

public class AddressMapper {
  public static AddressBO toBO(AddressDTO dto) {
    AddressBO bo = new AddressBO(dto.street(), dto.neighborhood(), dto.zipCode(), dto.number(), dto.complement(),
        dto.city(), dto.state());

    return bo;
  }

  public static AddressBO toBO(Address dto) {
    AddressBO bo = new AddressBO(dto.getStreet(), dto.getNeighborhood(), dto.getZipCode(), dto.getNumber(), dto.getComplement(),
        dto.getCity(), dto.getState());

    return bo;
  }


  public static Address toEntity(AddressBO bo) {
    Address entity = new Address(bo.getStreet(), bo.getNeighborhood(), bo.getZipCode(), bo.getNumber(), bo.getComplement(),
        bo.getCity(), bo.getState());

    return entity;
  }
}
