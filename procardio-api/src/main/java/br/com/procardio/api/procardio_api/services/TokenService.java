package br.com.procardio.api.procardio_api.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.procardio.api.procardio_api.models.Usuario;

@Service
public class TokenService {

    // Injetar o valor do segredo do token a partir do application.properties
    @Value("${api.security.token.secret}")
    private String secret;

    // Método para gerar o token JWT para um usuário autenticado
    public String gerarToken(Usuario usuario) {
        // Gera o token JWT usando a biblioteca Auth0
        try {
            // Definir o algoritmo de assinatura com o segredo
            Algorithm algoritimo = Algorithm.HMAC256(secret);

            // Construir o token com as informações do usuário
            return JWT.create()
                    // Definir o assunto (sujeito) do token como o email do usuário
                    .withSubject(usuario.getEmail())
                    // Definir o emissor do token
                    .withIssuer("API ProCardio")
                    // Definir a data de expiração do token
                    .withExpiresAt(dataExpiracao())
                    // Assinar o token com o algoritmo definido
                    .sign(algoritimo);
        } catch (JWTCreationException ex) {
            // Em caso de erro na criação do token, lançar uma exceção
            throw new RuntimeException("Erro ao gerar o token JWT");

        }
    }
    
    // Método para validar o token JWT e extrair o assunto (email do usuário)
    public String getSubject(String jwt) {
        try {
            // Definir o algoritmo de assinatura com o segredo
            Algorithm algoritimo = Algorithm.HMAC256(secret);

            // Verificar e decodificar o token JWT
            return JWT.require(algoritimo)
                    .withIssuer("API ProCardio")
                    .build()
                    .verify(jwt)
                    .getSubject();

        } catch (JWTVerificationException ex) {
            // Em caso de erro na verificação do token, lançar uma exceção
            throw new RuntimeException("Token JWT inválido ou expirado");

        }
    }

    // Método para calcular a data de expiração do token (2 horas a partir do momento atual)
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
