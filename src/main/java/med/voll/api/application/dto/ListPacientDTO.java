package med.voll.api.application.dto;

import med.voll.api.domain.entities.PacientBO;

public record ListPacientDTO(Long id, String name, String email, String cpf) {

    public ListPacientDTO(PacientBO pacient) {
        this(pacient.getId(), pacient.getName(), pacient.getEmail(), pacient.getCpf());
    }

}
