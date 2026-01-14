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

    public Consulta buscarConsultaPorId(Long id) {
        return consultaRepository.findById(id).orElse(null);
    }

    public List<Consulta> buscarConsultasPorMedico(Long medicoId) {
        return consultaRepository.findByMedico_Id(medicoId);
    }

    public Consulta buscarConsultaPorMedicoEDataHora(Long medicoId, LocalDateTime dataHora) {
        return consultaRepository.findByMedico_IdAndDataHora(medicoId, dataHora).orElse(null);
    }

    public Consulta salvarConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public void deletarConsulta(Long id) {
        consultaRepository.deleteById(id);
    }

}
