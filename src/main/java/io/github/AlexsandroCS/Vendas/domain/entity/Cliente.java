package io.github.AlexsandroCS.Vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "cliente")
public class Cliente {

    public Cliente(String name,Integer cpf){
        this.nome = name;
        this.cpf = cpf;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "cpf", length = 11)
    private Integer cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Pedido> pedidos;

    @Override
    public String toString() {
        return "ID do Cliente: "+id+" - "+"Nome do Cliente: "+nome+" - "+"CPF do Cliente: "+cpf;
    }
}
