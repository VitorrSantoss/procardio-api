package br.com.procardio.api.procardio_api.dtos;

public record ViaCepDTO(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf) {

}