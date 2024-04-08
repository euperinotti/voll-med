package med.voll.api.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
  private String street;
  private String neighborhood;
  private String zipCode;
  private String number;
  private String complement;
  private String city;
  private String state;
}
