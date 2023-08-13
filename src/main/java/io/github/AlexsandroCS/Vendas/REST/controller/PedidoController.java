package io.github.AlexsandroCS.Vendas.REST.controller;

import io.github.AlexsandroCS.Vendas.REST.DTO.PedidoDTO;
import io.github.AlexsandroCS.Vendas.domain.entity.Pedido;
import io.github.AlexsandroCS.Vendas.domain.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping(value = "/api/pedidos")
    public class PedidoController {

        private PedidoService service;

        public PedidoController(PedidoService service){
            this.service = service;
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Integer save(@RequestBody PedidoDTO pedidoDTO){
            Pedido pedido = service.salvar(pedidoDTO);
            return pedido.getId();
        }
    }
