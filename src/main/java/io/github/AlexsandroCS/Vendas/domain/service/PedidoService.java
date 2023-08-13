package io.github.AlexsandroCS.Vendas.domain.service;

import io.github.AlexsandroCS.Vendas.REST.DTO.PedidoDTO;
import io.github.AlexsandroCS.Vendas.domain.entity.Pedido;

public interface PedidoService {
    Pedido salvar(PedidoDTO DTO);
}

