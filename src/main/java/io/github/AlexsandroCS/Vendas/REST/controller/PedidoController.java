package io.github.AlexsandroCS.Vendas.REST.controller;

import io.github.AlexsandroCS.Vendas.REST.DTO.AtualizacaoStatusDTO;
import io.github.AlexsandroCS.Vendas.REST.DTO.InformacoesItemPedidoDTO;
import io.github.AlexsandroCS.Vendas.REST.DTO.InformacoesPedidoDTO;
import io.github.AlexsandroCS.Vendas.REST.DTO.PedidoDTO;
import io.github.AlexsandroCS.Vendas.domain.ENUM.StatusPedido;
import io.github.AlexsandroCS.Vendas.domain.entity.ItemPedido;
import io.github.AlexsandroCS.Vendas.domain.entity.Pedido;
import io.github.AlexsandroCS.Vendas.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO pedidoDTO) {
        Pedido pedido = service.salvar(pedidoDTO);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable("id") Integer id) {
        return service.obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não foi encontrado!"));
    }

    private InformacoesPedidoDTO converter(Pedido p) {
        return InformacoesPedidoDTO.builder()
                .codigo(p.getId())
                .dataPedido(p.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(p.getCliente().getCpf())
                .nomeCliente(p.getCliente().getNome())
                .total(p.getTotal())
                .status(p.getStatus().name())

                .itens(converterItens(p.getItens()))
                .build();
    }

    private List<InformacoesItemPedidoDTO> converterItens(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens.stream().map(item -> InformacoesItemPedidoDTO.builder()
                .descricaoProduto(item.getProduto().getDescricao())
                .precoUnitario(item.getProduto().getPreco())
                .quantidade(item.getQuantidade())
                .build()
        ).collect(Collectors.toList());
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable("id") Integer id, @RequestBody AtualizacaoStatusDTO statusDTO){
        String novoStatus = statusDTO.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }
}