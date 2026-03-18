package br.com.procardio.api.procardio_api.enums;

public enum Perfil {

    // Define os perfis de usuário com seus respectivos papéis (roles)
    ADMIN("ROLE_ADMIN"),
    MEDICO("ROLE_MEDICO"),
    PACIENTE("ROLE_PACIENTE");

    // Atributo que armazena o papel (role) do perfil
    private String role;

    // Construtor do enum que inicializa o papel (role)
    Perfil(String role) {
        this.role = role;
    }

    // Método para recuperar o papel (role) do perfil
    public String getRole() {
        return role;
    }
}
