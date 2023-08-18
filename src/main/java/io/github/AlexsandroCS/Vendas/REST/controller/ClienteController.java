package io.github.AlexsandroCS.Vendas.REST.controller;

import io.github.AlexsandroCS.Vendas.domain.entity.Cliente;
import io.github.AlexsandroCS.Vendas.domain.repository.Clientes;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes){
        this.clientes = clientes;
    }

    @GetMapping(value = "{id}")
    public Cliente getClienteById(@PathVariable("id") Integer idCliente){
        return clientes.findById(idCliente).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente not found!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente saveCliente(@RequestBody @Valid Cliente cliente){
        return clientes.save(cliente);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaCliente(@PathVariable(value = "id") Integer id){
        clientes.findById(id).map( clienteADeletar -> {
            clientes.delete(clienteADeletar);
            return clienteADeletar;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente not found!"));
    }

    @PutMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modificaCliente(@PathVariable Integer id, @RequestBody @Valid Cliente cliente){
        clientes.findById(id).map(clienteExistente -> {
            cliente.setId(clienteExistente.getId());
            clientes.save(cliente);
            return cliente;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente not found!"));
    }

    @GetMapping
    public List<Cliente> encontrarCliente(Cliente ClienteBuscado){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(ClienteBuscado,matcher);

        return clientes.findAll(example);
    }
}
