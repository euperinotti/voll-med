package med.voll.api.domain.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.application.dto.AddPacientDTO;
import med.voll.api.application.dto.UpdatePacientDTO;
import med.voll.api.domain.mappers.AddressMapper;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PacientBO {
  private Long id;
  private String name;
  private String email;
  private String phone;
  private String cpf;
  private AddressBO address;
  private Boolean isActive;

  public PacientBO(AddPacientDTO dto) {
    this.isActive = true;
    this.name = dto.name();
    this.email = dto.email();
    this.phone = dto.phone();
    this.cpf = dto.cpf();
    this.address = AddressMapper.toBO(dto.address());
  }

  public void atualizarInformacoes(UpdatePacientDTO dto) {
    if (dto.name() != null) {
      this.name = dto.name();
    }
    if (dto.phone() != null) {
      this.phone = dto.phone();
    }
    if (dto.phone() != null) {
      this.address = AddressMapper.toBO(dto.address());
    }

  }

  public void delete() {
    this.isActive = false;
  }
}
