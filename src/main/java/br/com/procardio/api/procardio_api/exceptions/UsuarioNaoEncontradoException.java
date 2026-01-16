package br.com.procardio.api.procardio_api.exceptions;

// Exceção personalizada para usuário não encontrado
public class UsuarioNaoEncontradoException extends RuntimeException {
    
    // Construtor que recebe o ID do usuário não encontrado
    public UsuarioNaoEncontradoException(Long id) {
        super("Usuário com ID " + id + " não encontrado.");
    }

    // Construtor que recebe o email do usuário não encontrado
    public UsuarioNaoEncontradoException(String email) {
        super("Usuario com email " + email + " não encontrado.");
    }
}
