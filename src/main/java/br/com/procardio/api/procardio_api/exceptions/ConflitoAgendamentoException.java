package br.com.procardio.api.procardio_api.exceptions;

// Exceção personalizada para conflitos de agendamento
public class ConflitoAgendamentoException extends RuntimeException {
    
    // Construtor que recebe uma mensagem de erro
    public ConflitoAgendamentoException(String messagem) {
        super(messagem);
    }
}
