package med.voll.api.domain.dto;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.entities.AddressBO;
import med.voll.api.domain.enums.Specialty;

@Getter
@Setter
public class DoctorDTO {
  private Long id;
  private String name;
  private String email;
  private String phone;
  private String crm;
  private Specialty specialty;
  private AddressBO address;
  private Boolean isActive;
}
