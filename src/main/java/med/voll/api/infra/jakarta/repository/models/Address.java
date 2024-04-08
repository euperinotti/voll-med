package med.voll.api.infra.jakarta.repository.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.application.dto.AddressDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String neighborhood;

    @Column(name = "zip_code")
    private String zipCode;
    private String number;
    private String complement;
    private String city;
    private String state;

    public Address(AddressDTO dados) {
        this.street = dados.street();
        this.neighborhood = dados.neighborhood();
        this.zipCode = dados.zipCode();
        this.state = dados.state();
        this.city = dados.city();
        this.number = dados.number();
        this.complement = dados.complement();
    }

    public void updateInfo(AddressDTO dados) {
        if (dados.street() != null) {
            this.street = dados.street();
        }
        if (dados.neighborhood() != null) {
            this.neighborhood = dados.neighborhood();
        }
        if (dados.zipCode() != null) {
            this.zipCode = dados.zipCode();
        }
        if (dados.state() != null) {
            this.state = dados.state();
        }
        if (dados.city() != null) {
            this.city = dados.city();
        }
        if (dados.number() != null) {
            this.number = dados.number();
        }
        if (dados.complement() != null) {
            this.complement = dados.complement();
        }
    }
}
