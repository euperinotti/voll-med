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
import med.voll.api.application.dto.AddDoctorDTO;
import med.voll.api.application.dto.DoctorDetailsDTO;
import med.voll.api.application.dto.ListDoctorDTO;
import med.voll.api.application.dto.UpdateDoctorDTO;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.infra.repository.DoctorRepository;

@RestController
@RequestMapping("doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

  @Autowired
  private DoctorRepository repository;

  @PostMapping
  @Transactional
  public ResponseEntity<DoctorDetailsDTO> add(@RequestBody @Valid AddDoctorDTO data,
      UriComponentsBuilder uriComponentsBuilder) {
    Doctor doctor = new Doctor(data);
    repository.save(doctor);

    URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(doctor.getId()).toUri();

    return ResponseEntity.created(uri).body(new DoctorDetailsDTO(doctor));

  }

  @GetMapping
  public ResponseEntity<Page<ListDoctorDTO>> list(
      @PageableDefault(size = 10, sort = { "name" }) Pageable pageConfig) {
    Page<ListDoctorDTO> page = repository.findAllByisActiveTrue(pageConfig).map(ListDoctorDTO::new);
    return ResponseEntity.ok(page);
  }

  @PutMapping
  @Transactional
  public ResponseEntity<DoctorDetailsDTO> update(@RequestBody @Valid UpdateDoctorDTO data) {
    Doctor doctor = repository.getReferenceById(data.id());
    doctor.updateInfo(data);

    return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<Doctor> delete(@PathVariable Long id) {
    Doctor doctor = repository.getReferenceById(id);
    doctor.delete();

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<DoctorDetailsDTO> detail(@PathVariable Long id) {
    Doctor doctor = repository.getReferenceById(id);

    return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
  }

}
