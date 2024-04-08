package med.voll.api.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.application.dto.AppointmentDetailsDTO;
import med.voll.api.application.dto.CancelAppointmentDTO;
import med.voll.api.application.dto.ScheduleAppointmentDTO;
import med.voll.api.application.error.InvalidParamException;
import med.voll.api.application.usecases.appointment.IScheduleAppointmentUseCase;
import med.voll.api.application.usecases.appointment.cancel.ICancelAppointmentUseCase;
import med.voll.api.domain.entities.Appointment;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.Pacient;
import med.voll.api.infra.jpa.repository.AppointmentRepository;
import med.voll.api.infra.jpa.repository.DoctorRepository;
import med.voll.api.infra.jpa.repository.PacientRepository;

@Service
public class AppointmentService {

  @Autowired
  private AppointmentRepository repository;

  @Autowired
  private DoctorRepository doctorRepository;

  @Autowired
  private PacientRepository pacientRepository;

  @Autowired
  private List<IScheduleAppointmentUseCase> usecases;

  @Autowired
  private List<ICancelAppointmentUseCase> cancelamentoConsultaUseCases;

  public AppointmentDetailsDTO schedule(ScheduleAppointmentDTO body) throws InvalidParamException {

    boolean pacientExists = pacientRepository.existsById(body.pacientId());
    boolean doctorExists = doctorRepository.existsById(body.doctorId());

    if (!pacientExists) {
      throw new InvalidParamException("Paciente não encontrado");
    }

    if (body.doctorId() != null && !doctorExists) {
      throw new InvalidParamException("Médico não encontrado");
    }

    usecases.forEach(usecase -> usecase.execute(body));

    Pacient pacient = pacientRepository.findById(body.pacientId()).get();
    Doctor doctor = choseDoctor(body);

    Appointment appointment = new Appointment(null, doctor, pacient, body.date(), null);

    repository.save(appointment);

    return new AppointmentDetailsDTO(appointment);

  }

  public void cancel(CancelAppointmentDTO body) throws InvalidParamException {
    if (!repository.existsById(body.appointmentId())) {
      throw new InvalidParamException("Id da consulta informado não existe!");
    }

    cancelamentoConsultaUseCases.forEach(v -> v.execute(body));

    Appointment appointment = repository.getReferenceById(body.appointmentId());
    appointment.cancel(body.reason());
  }

  private Doctor choseDoctor(ScheduleAppointmentDTO dto) throws InvalidParamException {
    if (dto.doctorId() != null) {
      return doctorRepository.findById(dto.doctorId()).get();
    }

    if (dto.specialty() == null) {
      throw new InvalidParamException("Especialidade não informada");
    }

    Doctor randomDoctor = doctorRepository.findRandomMedicAvailableOnDate(dto.specialty(), dto.date());

    if (randomDoctor == null) {
      throw new InvalidParamException("Não há médicos disponíveis na data informada");
    }
    return randomDoctor;
  }

}
