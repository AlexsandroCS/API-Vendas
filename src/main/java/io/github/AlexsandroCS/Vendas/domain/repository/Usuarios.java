package io.github.AlexsandroCS.Vendas.domain.repository;

import io.github.AlexsandroCS.Vendas.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface Usuarios extends JpaRepository<Usuario, Integer> {

    //A busca no banco precisa retornar um User Details.
    UserDetails findByLogin(String login);
}
