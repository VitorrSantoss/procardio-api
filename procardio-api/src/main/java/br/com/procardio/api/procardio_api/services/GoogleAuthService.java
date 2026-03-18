package br.com.procardio.api.procardio_api.services;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import br.com.procardio.api.procardio_api.enums.Perfil;
import br.com.procardio.api.procardio_api.models.Usuario;
import br.com.procardio.api.procardio_api.repository.UsuarioRepository;

@Service
public class GoogleAuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario processarUsuarioGoogle(OAuth2User oAuth2User) {
        // Extrair informações do usuário do Google
        String email = oAuth2User.getAttribute("email");
        // Extrair o nome do usuário do Google
        String nome = oAuth2User.getAttribute("name");

        // Verificar se o usuário já existe no banco de dados do google
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);

        // Se o usuário não existir, criar um novo usuário
        if (Objects.isNull(usuario)) {
            usuario = new Usuario();

            usuario.setNome(nome); // Definir o nome do usuário
            usuario.setEmail(email); // Definir o email do usuário
            usuario.setSenha(passwordEncoder.encode(UUID.randomUUID().toString())); // Gerar uma senha aleatória segura
            Set<Perfil> perfis = new HashSet<>(); // Atribuir o perfil de PACIENTE
            perfis.add(Perfil.PACIENTE); // Adicionar o perfil de PACIENTE
            usuario.setPerfis(perfis); // Definir os perfis do usuário


            return usuarioRepository.save(usuario); // Salvar o novo usuário no banco de dados
        }

        return usuario;
    }

}
