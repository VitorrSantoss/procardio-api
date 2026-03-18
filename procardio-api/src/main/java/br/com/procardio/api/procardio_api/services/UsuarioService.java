package br.com.procardio.api.procardio_api.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.procardio.api.procardio_api.dtos.UsuarioDTO;
import br.com.procardio.api.procardio_api.dtos.ViaCepDTO;
import br.com.procardio.api.procardio_api.exceptions.UsuarioNaoEncontradoException;
import br.com.procardio.api.procardio_api.models.Endereco;
import br.com.procardio.api.procardio_api.models.Usuario;
import br.com.procardio.api.procardio_api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired // Injetar o PasswordEncoder para criptografar senhas
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ViaCepService viaCepService;

    // Serviço para gerenciar operações relacionadas ao usuário (CADASTRAR)
    public Usuario salvarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        usuario = usuario.toModel(usuarioDTO);
        // Criptografar a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.senha()));

        if (Objects.nonNull(usuarioDTO.cep()) || !usuarioDTO.cep().isBlank()) {
            preencherCamposEndereco(usuario, usuarioDTO);
        }

        return usuarioRepository.save(usuario);
    }

    // Serviço para gerenciar operações relacionadas ao usuário (ATUALIZAR)
    public Usuario salvarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = buscarUsuarioPorId(id);

        // Verificar se o usuário existe antes de atualizar
        if (Objects.nonNull(usuario)) {
            usuario = usuario.toModel(usuarioDTO);
            // Garantir que o ID permaneça o mesmo durante a atualização
            usuario.setId(id);
            // Criptografar a senha antes de salvar
            usuario.setSenha(passwordEncoder.encode(usuarioDTO.senha()));

            if (Objects.nonNull(usuarioDTO.cep()) || !usuarioDTO.cep().isBlank()) {
                preencherCamposEndereco(usuario, usuarioDTO);
            }

            return usuarioRepository.save(usuario);
        }

        // Lançar exceção se o usuário não for encontrado
        throw new UsuarioNaoEncontradoException(id);
    }

    // LISTAR TODOS OS USUÁRIOS
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // BUSCAR USUÁRIO POR ID
    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // DELETAR USUÁRIO
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // BUSCAR USUÁRIO POR EMAIL
    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    // BUSCAR USUÁRIOS POR NOME
    public List<Usuario> buscarUsuariosPorNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);
    }

    private void preencherCamposEndereco(Usuario usuario, UsuarioDTO usuarioDTO) {
        ViaCepDTO enderecoDTO = viaCepService.obterDadosEnderecoPeloCep(usuarioDTO.cep());

        if (Objects.nonNull(enderecoDTO)) {
            Endereco endereco = new Endereco();

            endereco.setBairro(enderecoDTO.bairro());
            endereco.setCidade(enderecoDTO.localidade());
            endereco.setEstado(enderecoDTO.uf());
            endereco.setLogradouro(enderecoDTO.logradouro());
            endereco.setCep(enderecoDTO.cep());

            endereco.setNumero(usuarioDTO.numero());
            endereco.setComplemento(usuarioDTO.complemento());

            usuario.setEndereco(endereco);
        }
    }

}
