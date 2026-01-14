package br.com.procardio.api.procardio_api.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable // Indica que essa classe pode ser incorporada em outras entidades
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String numero;
    private String complemento;

}
