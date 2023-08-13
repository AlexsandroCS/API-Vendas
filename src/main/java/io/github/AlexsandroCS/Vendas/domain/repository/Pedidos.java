package io.github.AlexsandroCS.Vendas.domain.repository;

import io.github.AlexsandroCS.Vendas.domain.entity.Cliente;
import io.github.AlexsandroCS.Vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
