package io.github.AlexsandroCS.Vendas.exception;

public class PedidoException extends RuntimeException{
    public PedidoException() {
        super("Pedido não encontrado!");
    }
}
