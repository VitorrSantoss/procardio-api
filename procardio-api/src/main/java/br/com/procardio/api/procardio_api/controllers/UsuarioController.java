package br.com.procardio.api.procardio_api.controllers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.procardio.api.procardio_api.dtos.UsuarioDTO;
import br.com.procardio.api.procardio_api.dtos.UsuarioResponseDTO;
import br.com.procardio.api.procardio_api.models.Usuario;
import br.com.procardio.api.procardio_api.services.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Apenas usuários com a função ADMIN podem acessar este endpoint
    @PreAuthorize("hasAnyRole('ADMIN', 'PACIENTE')")
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

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios()
                .stream()
                .map(u -> u.toDTO())
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id).toDTO());
    }

}
