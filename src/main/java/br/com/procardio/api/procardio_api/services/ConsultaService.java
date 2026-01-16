package br.com.procardio.api.procardio_api.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.procardio.api.procardio_api.models.Consulta;
import br.com.procardio.api.procardio_api.repository.ConsultaRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    // BUSCAR CONSULTA POR ID
    public Consulta buscarConsultaPorId(Long id) {
        return consultaRepository.findById(id).orElse(null);
    }

    // BUSCAR CONSULTAS POR MÉDICO
    public List<Consulta> buscarConsultasPorMedico(Long medicoId) {
        return consultaRepository.findByMedico_Id(medicoId);
    }

    // BUSCAR CONSULTA POR MÉDICO E DATA/HORA
    public Consulta buscarConsultaPorMedicoEDataHora(Long medicoId, LocalDateTime dataHora) {
        return consultaRepository.findByMedico_IdAndDataHora(medicoId, dataHora).orElse(null);
    }

    // SALVAR CONSULTA
    public Consulta salvarConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    // DELETAR CONSULTA
    public void deletarConsulta(Long id) {
        consultaRepository.deleteById(id);
    }

}
