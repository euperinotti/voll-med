package med.voll.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.application.dto.UpdatePacientDTO;
import med.voll.api.domain.entities.PacientBO;
import med.voll.api.infra.jakarta.repository.models.Pacient;
import med.voll.api.infra.jpa.repository.PacientRepository;
import med.voll.api.application.dto.AddPacientDTO;
import med.voll.api.application.dto.PacientDetailsDTO;
import med.voll.api.application.dto.ListPacientDTO;

@RestController
@RequestMapping("pacients")
@SecurityRequirement(name = "bearer-key")
public class PacientController {

    @Autowired
    private PacientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<PacientDetailsDTO> add(@RequestBody @Valid AddPacientDTO data, UriComponentsBuilder uriComponentsBuilder) {
        Pacient pacient = new Pacient(data);
        repository.save(pacient);

        URI uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(pacient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PacientDetailsDTO(pacient));
    }

    @GetMapping
    public ResponseEntity<Page<ListPacientDTO>> listAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageConfig) {

        Page<ListPacientDTO> page = repository.findAllByisActiveTrue(pageConfig).map(ListPacientDTO::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PacientDetailsDTO> update(@RequestBody @Valid UpdatePacientDTO data) {
        Pacient pacient = repository.getReferenceById(data.id());
        pacient.atualizarInformacoes(data);

        return ResponseEntity.ok(new PacientDetailsDTO(pacient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Pacient pacient = repository.getReferenceById(id);
        pacient.delete();

        return ResponseEntity.noContent().build();
    }


}
