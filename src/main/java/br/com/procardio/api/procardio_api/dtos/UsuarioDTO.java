package br.com.procardio.api.procardio_api.dtos;

import jakarta.validation.constraints.NotBlank;

// DTO para transferência de dados do usuário
public record UsuarioDTO(
        @NotBlank String nome,
        @NotBlank String email,
        @NotBlank String senha,
        String cep,
        String numero,
        String complemento) {
}
