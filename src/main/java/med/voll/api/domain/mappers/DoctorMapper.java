package med.voll.api.domain.mappers;

import med.voll.api.application.dto.AddDoctorDTO;
import med.voll.api.domain.dto.DoctorDTO;
import med.voll.api.domain.entities.DoctorBO;

public class DoctorMapper {
  public static DoctorBO toBO(DoctorDTO dto) {
    DoctorBO bo = new DoctorBO(dto.getId(), dto.getName(), dto.getEmail(), dto.getPhone(), dto.getCrm(),
        dto.getSpecialty(), dto.getAddress(), dto.getIsActive());

    return bo;
  }

  public static DoctorBO toBO(AddDoctorDTO dto) {
    DoctorBO bo = new DoctorBO(dto.name(), dto.email(), dto.phone(), dto.crm(), dto.specialty(),
        AddressMapper.toBO(dto.address()));

    return bo;
  }
}
