package io.github.AlexsandroCS.Vendas.domain.repository;

import io.github.AlexsandroCS.Vendas.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidos extends JpaRepository<ItemPedido,Integer> {

}
