package br.com.procardio.api.procardio_api.dtos;

// DTO de resposta para informações do usuário
public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        EnderecoDTO endereco) {

}
