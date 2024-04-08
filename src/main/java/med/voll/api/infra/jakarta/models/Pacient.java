package med.voll.api.infra.jakarta.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.application.dto.UpdatePacientDTO;
import med.voll.api.application.dto.AddPacientDTO;

@Table(name = "pacients")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pacient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;

  private String phone;

  private String cpf;

  @Embedded
  private Address address;

  private Boolean isActive;

  public Pacient(AddPacientDTO dto) {
    this.isActive = true;
    this.name = dto.name();
    this.email = dto.email();
    this.phone = dto.phone();
    this.cpf = dto.cpf();
    this.address = new Address(dto.address());
  }

  public void atualizarInformacoes(UpdatePacientDTO dto) {
    if (dto.name() != null) {
      this.name = dto.name();
    }
    if (dto.phone() != null) {
      this.phone = dto.phone();
    }
    if (dto.phone() != null) {
      this.address.updateInfo(dto.address());
    }

  }

  public void delete() {
    this.isActive = false;
  }
}
