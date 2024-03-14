package med.voll.api.application.usecases.consulta;

import med.voll.api.application.dto.DadosAgendamentoConsulta;
import med.voll.api.infra.repository.ConsultaRepository;

public class MedicoComConsultaNoMesmoHorarioUseCase implements IConsultaUseCase {

  private ConsultaRepository repository;

  @Override
  public void execute(DadosAgendamentoConsulta dados) {
    boolean hasOtherAppointmentSchedule = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

    if (hasOtherAppointmentSchedule) { 
      throw new RuntimeException("Já existe uma consulta no mesmo horário");
    }
  }
  
}
