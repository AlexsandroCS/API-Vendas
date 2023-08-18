package io.github.AlexsandroCS.Vendas.REST.controller;

import io.github.AlexsandroCS.Vendas.REST.APIErrors;
import io.github.AlexsandroCS.Vendas.exception.ExceptionError;
import io.github.AlexsandroCS.Vendas.exception.PedidoException;
import org.apache.el.stream.Stream;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RESTControllerAdvice {

    @ExceptionHandler(value = ExceptionError.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public APIErrors handleBusinessRulesException(ExceptionError errors) {
        String mensagemErro = errors.getMessage();
        return new APIErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIErrors handlePedidoNotFoundException(PedidoException errors){
        return new APIErrors(errors.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIErrors handleMethodNotValidException(MethodArgumentNotValidException exception){
        List<String> errors = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        return new APIErrors(errors);
    }
}