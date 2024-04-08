package med.voll.api.infra.jakarta.repository.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.application.dto.AddDoctorDTO;
import med.voll.api.application.dto.UpdateDoctorDTO;
import med.voll.api.domain.enums.Specialty;

@Table(name = "doctors")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    private String phone;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    private Boolean isActive;

    public Doctor(AddDoctorDTO dto) {
        this.isActive = true;
        this.name = dto.name();
        this.email = dto.email();
        this.phone = dto.phone();
        this.crm = dto.crm();
        this.specialty = dto.specialty();
        this.address = new Address(dto.address());
    }

    public void updateInfo(UpdateDoctorDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.phone() != null) {
            this.phone = dto.phone();
        }
        if (dto.address() != null) {
            this.address.updateInfo(dto.address());
        }

    }

    public void delete() {
        this.isActive = false;
    }
}
