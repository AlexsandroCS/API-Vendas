package io.github.AlexsandroCS.Vendas.domain.repository;

import io.github.AlexsandroCS.Vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {

}
