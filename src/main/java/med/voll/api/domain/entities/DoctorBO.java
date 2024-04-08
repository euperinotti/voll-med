package med.voll.api.domain.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.enums.Specialty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DoctorBO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;
    private Specialty specialty;
    private AddressBO address;
    private Boolean isActive;

    public DoctorBO(String name, String email, String phone, String crm, Specialty specialty, AddressBO address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.crm = crm;
        this.specialty = specialty;
        this.address = address;
        this.isActive = true;
    }

    public void delete() {
        this.isActive = false;
    }
}
