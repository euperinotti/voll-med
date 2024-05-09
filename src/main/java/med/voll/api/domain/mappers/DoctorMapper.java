package med.voll.api.domain.mappers;

import med.voll.api.application.dto.AddDoctorDTO;
import med.voll.api.application.dto.DoctorDetailsDTO;
import med.voll.api.domain.dto.DoctorDTO;
import med.voll.api.domain.entities.DoctorBO;
import med.voll.api.infra.jakarta.repository.models.Doctor;

public class DoctorMapper {
  public static DoctorBO toBO(DoctorDTO dto) {
    DoctorBO bo = new DoctorBO(dto.getId(), dto.getName(), dto.getEmail(), dto.getPhone(), dto.getCrm(),
        dto.getSpecialty(), dto.getAddress(), dto.getIsActive());

    return bo;
  }

  public static DoctorBO toBO(DoctorDetailsDTO dto) {
    DoctorBO bo = new DoctorBO(dto.id(), dto.name(), dto.email(), dto.phone(), dto.crm(),
        dto.specialty(), dto.address(), null);

    return bo;
  }

  public static DoctorBO toBO(AddDoctorDTO dto) {
    DoctorBO bo = new DoctorBO(dto.name(), dto.email(), dto.phone(), dto.crm(), dto.specialty(),
        AddressMapper.toBO(dto.address()));

    return bo;
  }

  public static Doctor toEntity(DoctorBO dto) {
    Doctor bo = new Doctor(dto.getId(), dto.getName(), dto.getEmail(), dto.getPhone(), dto.getCrm(),
        dto.getSpecialty(), AddressMapper.toEntity(dto.getAddress()), dto.getIsActive());

    return bo;
  }

}
