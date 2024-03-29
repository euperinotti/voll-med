package med.voll.api.application.dto;

import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.Specialty;

public record ListDoctorDTO(Long id, String name, String email, String crm, Specialty specialty) {

    public ListDoctorDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }

}
