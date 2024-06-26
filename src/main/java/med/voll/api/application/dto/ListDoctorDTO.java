package med.voll.api.application.dto;

import med.voll.api.domain.enums.Specialty;
import med.voll.api.infra.jakarta.repository.models.Doctor;

public record ListDoctorDTO(Long id, String name, String email, String crm, Specialty specialty) {

    public ListDoctorDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }

}
