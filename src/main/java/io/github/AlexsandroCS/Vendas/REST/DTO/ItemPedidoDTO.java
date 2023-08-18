package io.github.AlexsandroCS.Vendas.REST.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemPedidoDTO {

    @NotNull(message = "Informe o produto desejado!")
    private Integer produto;

    @NotNull(message = "Informe a quantidade desejada do produto!")
    private Integer quantidade;
}

