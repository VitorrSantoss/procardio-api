package br.com.procardio.api.procardio_api.models;

import br.com.procardio.api.procardio_api.dtos.UsuarioDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Embedded // Incorporação do endereço como um componente embutido
    private Endereco endereco;

    // Método para converter UsuarioDTO em Usuario
    public Usuario toModel(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        
        // Mapeamento dos campos do DTO para a entidade
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());

        // Mapeamento do endereço se fornecido
        if (dto.cep() != null || dto.numero() != null || dto.complemento() != null) {
            Endereco endereco = new Endereco();

            endereco.setCep(dto.cep());
            endereco.setNumero(dto.numero());
            endereco.setComplemento(dto.complemento());

            usuario.setEndereco(endereco);
        }

        // Retorna a entidade Usuario preenchida
        return usuario;
    }

}
