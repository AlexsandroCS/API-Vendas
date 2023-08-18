package io.github.AlexsandroCS.Vendas.REST;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class APIErrors {

    @Getter
    private List<String> errors;

    public APIErrors(String mensagemErro) {
        this.errors = Arrays.asList(mensagemErro);
    }

    public APIErrors(List<String> errors) {
        this.errors = errors;
    }
}
