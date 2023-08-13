package io.github.AlexsandroCS.Vendas;

import io.github.AlexsandroCS.Vendas.REST.controller.ClienteController;
import io.github.AlexsandroCS.Vendas.domain.entity.Cliente;
import io.github.AlexsandroCS.Vendas.domain.entity.Pedido;
import io.github.AlexsandroCS.Vendas.domain.repository.Clientes;
import io.github.AlexsandroCS.Vendas.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired Clientes clientes, @Autowired Pedidos pedidos, @Autowired ClienteController controllerCliente){
		return args ->{

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}