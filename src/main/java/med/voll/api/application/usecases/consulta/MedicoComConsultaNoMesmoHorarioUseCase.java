package med.voll.api.application.usecases.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.application.dto.DadosAgendamentoConsulta;
import med.voll.api.infra.repository.ConsultaRepository;

@Component
public class MedicoComConsultaNoMesmoHorarioUseCase implements IConsultaUseCase {

  @Autowired
  private ConsultaRepository repository;

  @Override
  public void execute(DadosAgendamentoConsulta dados) {
    boolean hasOtherAppointmentSchedule = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());

    if (hasOtherAppointmentSchedule) { 
      throw new RuntimeException("Já existe uma consulta no mesmo horário");
    }
  }
  
}
