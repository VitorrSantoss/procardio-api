package br.com.procardio.api.procardio_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.procardio.api.procardio_api.enums.Especialidade;
import br.com.procardio.api.procardio_api.models.Medico;
import br.com.procardio.api.procardio_api.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    // BUSCAR MÉDICO POR ID
    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id).orElse(null);
    }

    // BUSCAR MÉDICO POR EMAIL
    public Medico buscarMedicoPorEmail(String email) {
        return medicoRepository.findByEmail(email).orElse(null);
    }

    // BUSCAR MÉDICO POR CRM
    public Medico buscarMedicoPorCrm(String crm) {
        return medicoRepository.findByCrm(crm).orElse(null);
    }

    // BUSCAR MÉDICOS POR ESPECIALIDADE
    public List<Medico> buscarMedicosPorEspecialidade(Especialidade especialidade) {
        return medicoRepository.findByEspecialidade(especialidade);
    }

    // SALVAR MÉDICO
    public Medico salvarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    // DELETAR MÉDICO
    public void deletarMedico(Long id) {
        medicoRepository.deleteById(id);
    }

    // LISTAR TODOS OS MÉDICOS
    public List<Medico> listarTodosMedicos() {
        return medicoRepository.findAll();
    }

}
