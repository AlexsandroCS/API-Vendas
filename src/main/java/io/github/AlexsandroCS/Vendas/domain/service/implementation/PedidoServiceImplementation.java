package io.github.AlexsandroCS.Vendas.domain.service.implementation;

import io.github.AlexsandroCS.Vendas.REST.DTO.ItemPedidoDTO;
import io.github.AlexsandroCS.Vendas.REST.DTO.PedidoDTO;
import io.github.AlexsandroCS.Vendas.domain.entity.Cliente;
import io.github.AlexsandroCS.Vendas.domain.entity.ItemPedido;
import io.github.AlexsandroCS.Vendas.domain.entity.Pedido;
import io.github.AlexsandroCS.Vendas.domain.entity.Produto;
import io.github.AlexsandroCS.Vendas.domain.repository.Clientes;
import io.github.AlexsandroCS.Vendas.domain.repository.ItemPedidos;
import io.github.AlexsandroCS.Vendas.domain.repository.Pedidos;
import io.github.AlexsandroCS.Vendas.domain.repository.Produtos;
import io.github.AlexsandroCS.Vendas.domain.service.PedidoService;
import io.github.AlexsandroCS.Vendas.exception.ExpectionError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImplementation implements PedidoService {

    private final Pedidos repositoryPedidos;
    private final Clientes repositoryClientes;
    private final Produtos repositoryProdutos;
    private final ItemPedidos repositoryItemPedidos;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Integer idCliente = pedidoDTO.getCliente();
        Cliente cliente = repositoryClientes
                .findById(idCliente)
                .orElseThrow(() -> new ExpectionError("Cliente não encontrado em nossa base de dados!"));

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedidos = convertendoItens(pedido, pedidoDTO.getItens());
        repositoryPedidos.save(pedido);
        repositoryItemPedidos.saveAll(itensPedidos);
        pedido.setItens(itensPedidos);
        return pedido;
    }

    private List<ItemPedido> convertendoItens(Pedido pedido, List<ItemPedidoDTO> itens){
        if (itens.isEmpty()){
            throw new ExpectionError("Não é possível realizar um pedido sem item!");
        }

        return itens.stream().map(item -> {
            Integer idProduto = item.getProduto();
            Produto produto = repositoryProdutos
                    .findById(idProduto)
                    .orElseThrow(() -> new ExpectionError("Produto não encontrado em nossa base de dados! -> " + idProduto));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(item.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            return itemPedido;
        }).collect(Collectors.toList());
    }
}

