package med.voll.api.application.dto;

import med.voll.api.domain.entities.AddressBO;
import med.voll.api.domain.entities.DoctorBO;
import med.voll.api.domain.enums.Specialty;

public record DoctorDetailsDTO(Long id, String name, String email, String crm, String phone, Specialty specialty,
    AddressBO address) {

  public DoctorDetailsDTO(DoctorBO doctor) {
    this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhone(),
        doctor.getSpecialty(), doctor.getAddress());
  }
}
