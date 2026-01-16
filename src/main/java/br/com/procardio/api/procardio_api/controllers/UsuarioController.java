package br.com.procardio.api.procardio_api.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.procardio.api.procardio_api.dtos.UsuarioDTO;
import br.com.procardio.api.procardio_api.models.Usuario;
import br.com.procardio.api.procardio_api.services.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para cadastrar um novo usuário
    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        // Chama o serviço para salvar o novo usuário
        Usuario novoUsuario = usuarioService.salvarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    // Endpoint para atualizar um usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        // Chama o serviço para atualizar o usuário
        Usuario usuarioAtualizado = usuarioService.salvarUsuario(id, usuarioDTO);

        // Verifica se o usuário foi encontrado e atualizado
        if (Objects.nonNull(usuarioAtualizado)) {
            return ResponseEntity.ok(usuarioAtualizado);
        }
        return ResponseEntity.notFound().build();
    }
}
