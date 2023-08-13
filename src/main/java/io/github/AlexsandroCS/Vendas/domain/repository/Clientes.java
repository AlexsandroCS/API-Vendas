
package io.github.AlexsandroCS.Vendas.domain.repository;

import io.github.AlexsandroCS.Vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> {
    @Query(value = "SELECT c FROM Cliente c WHERE c.nome LIKE :nome")
    List<Cliente> encontrarPorNome(@Param(value = "nome") String nome);

    @Query(value = "DELETE FROM Cliente c WHERE c.nome = :nome")
    @Modifying
    void deleteByNome(@Param(value = "nome") String nome);

    @Query(value = "SELECT c FROM Cliente c LEFT JOIN FETCH c.pedidos WHERE c.id = :id")
    Cliente findClienteFetchPedidos(@Param(value = "id") Integer id);
}
