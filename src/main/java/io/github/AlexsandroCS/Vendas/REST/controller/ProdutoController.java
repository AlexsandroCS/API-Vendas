package io.github.AlexsandroCS.Vendas.REST.controller;

import io.github.AlexsandroCS.Vendas.domain.entity.Produto;
import io.github.AlexsandroCS.Vendas.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {

    private Produtos produtos;

    public ProdutoController(Produtos produtos){
        this.produtos = produtos;
    }

    @GetMapping(value = "{id}")
    public Produto capturaProduto(@PathVariable(value = "id") Integer idProduto){
        return produtos.findById(idProduto).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvarProduto(@RequestBody Produto novoProduto){
        return produtos.save(novoProduto);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaProduto(@PathVariable(value = "id") Integer idProduto){
        produtos.findById(idProduto).map(p -> {
            produtos.delete(p);
            return p;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

    @PutMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modificaProduto(@PathVariable(value = "id") Integer idProduto, @RequestBody Produto produto){
        produtos.findById(idProduto).map(p -> {
            produto.setId(p.getId());
            produtos.save(produto);
            return produto;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não pôde ser modificado!"));
    }

    @GetMapping
    public List<Produto> capturaProdutos(Produto produtoProcurado){
        ExampleMatcher exm = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example ex = Example.of(produtoProcurado, exm);

        return produtos.findAll(ex);
    }
}
