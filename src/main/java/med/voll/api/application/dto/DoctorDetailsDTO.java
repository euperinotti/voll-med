package med.voll.api.application.dto;

import med.voll.api.domain.entities.AddressBO;
import med.voll.api.domain.entities.DoctorBO;
import med.voll.api.domain.enums.Specialty;
import med.voll.api.domain.mappers.AddressMapper;
import med.voll.api.infra.jakarta.repository.models.Doctor;

public record DoctorDetailsDTO(Long id, String name, String email, String crm, String phone, Specialty specialty,
    AddressBO address) {

  public DoctorDetailsDTO(DoctorBO doctor) {
    this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhone(),
        doctor.getSpecialty(), doctor.getAddress());
  }

  public DoctorDetailsDTO(Doctor doctor) {
    this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhone(),
        doctor.getSpecialty(), AddressMapper.toBO(doctor.getAddress()));
  }
}
