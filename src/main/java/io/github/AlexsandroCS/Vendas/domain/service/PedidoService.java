package io.github.AlexsandroCS.Vendas.domain.service;

import io.github.AlexsandroCS.Vendas.REST.DTO.PedidoDTO;
import io.github.AlexsandroCS.Vendas.domain.ENUM.StatusPedido;
import io.github.AlexsandroCS.Vendas.domain.entity.Pedido;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO DTO);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido status);
}

