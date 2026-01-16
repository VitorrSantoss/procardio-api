package br.com.procardio.api.procardio_api.dtos;

import br.com.procardio.api.procardio_api.models.Usuario;

// DTO de resposta para informações do usuário
public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email) {

    // Construtor que mapeia de Usuario para UsuarioResponseDTO
    public UsuarioResponseDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail());
    }

}
