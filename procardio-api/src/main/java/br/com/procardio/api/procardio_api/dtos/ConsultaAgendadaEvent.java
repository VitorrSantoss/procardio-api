package br.com.procardio.api.procardio_api.dtos;

import java.time.LocalDateTime;

public record ConsultaAgendadaEvent(
        Long idConsulta,
        Long idPaciente,
        String nomePacinete,
        String emailPaciente,
        String nomeMedico,
        String especialidadeMedico,
        LocalDateTime dataHora) {

}
