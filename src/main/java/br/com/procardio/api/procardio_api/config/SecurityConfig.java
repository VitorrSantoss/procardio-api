package br.com.procardio.api.procardio_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    // Configuração de segurança HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configurações de segurança
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                // Adiciona o filtro de segurança personalizado antes do filtro de autenticação padrão
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    // Configuração do gerenciador de autenticação
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        // Retorna o gerenciador de autenticação a partir da configuração fornecida
        return configuration.getAuthenticationManager();
    }

    @Bean
    // Configuração do codificador de senhas
    public PasswordEncoder passwordEncoder() {
        // Retorna uma instância do BCryptPasswordEncoder para codificação de senhas
        return new BCryptPasswordEncoder();
    }
    
}
