package br.com.procardio.api.procardio_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.procardio.api.procardio_api.dtos.LoginDTO;
import br.com.procardio.api.procardio_api.dtos.TokenDTO;
import br.com.procardio.api.procardio_api.models.Usuario;
import br.com.procardio.api.procardio_api.services.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    // Injetar o serviço de token
    @Autowired
    private TokenService tokenService;

    // Injetar o gerenciador de autenticação
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> efetuarLogin(@Valid @RequestBody LoginDTO loginDTO) {
        // Lógica de autenticação e geração de token
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha());
        // Autenticar o usuário
        var authentication = authenticationManager.authenticate(authenticationToken);
        // Gerar o token JWT
        var tokenJWT = tokenService.gerarToken((Usuario)authentication.getPrincipal());

        // Retornar o token na resposta
        return ResponseEntity.ok().body(new TokenDTO(tokenJWT));
    }
    
}
