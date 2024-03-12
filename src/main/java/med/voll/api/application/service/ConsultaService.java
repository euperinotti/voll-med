package med.voll.api.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.application.dto.DadosAgendamentoConsulta;
import med.voll.api.application.dto.DadosCancelamentoConsulta;
import med.voll.api.application.error.InvalidParamException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.infra.repository.ConsultaRepository;
import med.voll.api.infra.repository.MedicoRepository;
import med.voll.api.infra.repository.PacienteRepository;

@Service
public class ConsultaService {

  @Autowired
  private ConsultaRepository repository;

  @Autowired
  private MedicoRepository medicoRepository;

  @Autowired
  private PacienteRepository pacienteRepository;
  
  public void agendar(DadosAgendamentoConsulta body) throws InvalidParamException {

    boolean pacienteExiste = pacienteRepository.existsById(body.idPaciente());
    boolean medicoExiste = medicoRepository.existsById(body.idMedico());

    if (!pacienteExiste) {
      throw new InvalidParamException("Paciente não encontrado");
    }

    if (body.idMedico() != null && !medicoExiste) {
      throw new InvalidParamException("Médico não encontrado");
    }

    Paciente paciente = pacienteRepository.findById(body.idPaciente()).get();
    Medico medico = medicoRepository.findById(body.idMedico()).get();

    Consulta consulta = new Consulta(null, medico, paciente, body.data(), null);
    
    repository.save(consulta);

  }

  public void cancelar(DadosCancelamentoConsulta dados) throws InvalidParamException {
    if (!repository.existsById(dados.idConsulta())) {
        throw new InvalidParamException("Id da consulta informado não existe!");
    }

    var consulta = repository.getReferenceById(dados.idConsulta());
    consulta.cancelar(dados.motivo());
}

  private Medico escolherMedico(DadosAgendamentoConsulta dados) throws InvalidParamException {
    if (dados.idMedico() != null) {
      return medicoRepository.findById(dados.idMedico()).get();
    }

    if (dados.especialidade() == null) {
      throw new InvalidParamException("Especialidade não informada");
    }

    return medicoRepository.findRandomMedicAvailableOnDate(dados.especialidade(), dados.data());
  }

}
