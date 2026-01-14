package br.com.procardio.api.procardio_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.procardio.api.procardio_api.enums.Especialidade;
import br.com.procardio.api.procardio_api.models.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    // Adicionar métodos de consulta personalizados conforme necessário

    // Adicionar método para buscar médico por email
    Optional<Medico> findByEmail(String email);

    // Adicionar método para buscar médico por CRM
    Optional<Medico> findByCrm(String crm);

    // Adicionar método para buscar médicos por nome (contendo, ignorando maiúsculas/minúsculas)
    List<Medico> findByNomeContainingIgnoreCase(String nome);

    // Adicionar método para buscar médicos por especialidade (contendo, ignorando maiúsculas/minúsculas)
    List<Medico> findByEspecialidade(Especialidade especialidade);

}
