package med.voll.api.application.dto;

import med.voll.api.domain.entities.Address;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.enums.Specialty;

public record DoctorDetailsDTO(Long id, String name, String email, String crm, String phone, Specialty specialty,
    Address address) {

  public DoctorDetailsDTO(Doctor doctor) {
    this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhone(),
        doctor.getSpecialty(), doctor.getAddress());
  }
}
