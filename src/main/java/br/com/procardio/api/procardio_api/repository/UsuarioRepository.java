package br.com.procardio.api.procardio_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.procardio.api.procardio_api.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    
    // Adicionar métodos de consulta personalizados conforme necessário

    // Adicionar método para buscar usuário por email
    Optional <Usuario> findByEmail(String email);

    // Adicionar método para buscar usuários por nome (contendo, ignorando maiúsculas/minúsculas)
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
}
