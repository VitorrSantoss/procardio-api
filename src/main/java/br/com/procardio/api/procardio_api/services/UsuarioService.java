package br.com.procardio.api.procardio_api.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.procardio.api.procardio_api.dtos.UsuarioDTO;
import br.com.procardio.api.procardio_api.exceptions.UsuarioNaoEncontradoException;
import br.com.procardio.api.procardio_api.models.Usuario;
import br.com.procardio.api.procardio_api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        usuario = usuario.toModel(usuarioDTO);

        return usuarioRepository.save(usuario);
    }

    public Usuario salvarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = buscarUsuarioPorId(id);

        if (Objects.nonNull(usuario)) {
            usuario = usuario.toModel(usuarioDTO);

            return usuarioRepository.save(usuario);
        }

        throw new UsuarioNaoEncontradoException(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    public List<Usuario> buscarUsuariosPorNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);
    }

}
