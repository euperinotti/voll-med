package med.voll.api.application.dto;

import med.voll.api.domain.entities.Pacient;

public record ListPacientDTO(Long id, String name, String email, String cpf) {

    public ListPacientDTO(Pacient pacient) {
        this(pacient.getId(), pacient.getName(), pacient.getEmail(), pacient.getCpf());
    }

}
