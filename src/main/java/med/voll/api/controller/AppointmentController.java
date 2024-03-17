package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.application.dto.AppointmentDetailsDTO;
import med.voll.api.application.dto.CancelAppointmentDTO;
import med.voll.api.application.dto.ScheduleAppointmentDTO;
import med.voll.api.application.error.InvalidParamException;
import med.voll.api.application.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

  @Autowired
  private AppointmentService service;

  @PostMapping
  @Transactional
  public ResponseEntity<AppointmentDetailsDTO> scheduleAppointment(@RequestBody @Valid ScheduleAppointmentDTO body) throws InvalidParamException {
    AppointmentDetailsDTO dto = service.schedule(body);
    return ResponseEntity.ok(dto);
  }

  @DeleteMapping
  @Transactional
  public ResponseEntity<Object> cancelAppointment(@RequestBody @Valid CancelAppointmentDTO body) throws InvalidParamException {
    service.cancel(body);
    return ResponseEntity.noContent().build();
  }
}
