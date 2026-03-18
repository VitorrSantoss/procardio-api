package br.com.procardio.api.procardio_api.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.procardio.api.procardio_api.models.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    // Adicionar métodos de consulta personalizados conforme necessário

    // Adicionar método para buscar consultas por médico
    List<Consulta> findByMedico_Id(Long medicoId);

    // Adicionar método para buscar consulta por médico e data/hora
    Optional<Consulta> findByMedico_IdAndDataHora(Long medicoId, LocalDateTime dataHora);

    // Adicionar método para buscar consultas por médico e intervalo de datas
    List<Consulta> findByMedico_IdAndDataHoraBetween(Long medicoId, LocalDateTime dataInicio, LocalDateTime dataFim);

    // Adicionar método para buscar consultas por paciente
    List<Consulta> findByPaciente_Id(Long pacienteId);

    // Adicionar método para buscar consultas por paciente e intervalo de datas
    List<Consulta> findByPaciente_IdAndDataHoraBetween(Long pacienteId, LocalDateTime dataInicio, LocalDateTime dataFim);

}
