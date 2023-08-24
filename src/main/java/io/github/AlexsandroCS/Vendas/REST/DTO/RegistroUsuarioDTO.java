package io.github.AlexsandroCS.Vendas.REST.DTO;

import io.github.AlexsandroCS.Vendas.domain.ENUM.UserRoles;

public record RegistroUsuarioDTO(String login, String senha, UserRoles tipoUsuario) {
}
