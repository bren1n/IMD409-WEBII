package com.jeanlima.springrestapiapp.repository;

import com.jeanlima.springrestapiapp.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
    @Query("select e from Estoque e where e.produto.descricao like %:produto%")
    public List<Estoque> findAllByProdutoNome(String produto);

    @Query("select e from Estoque e where e.produto.id = :id")
    public Optional<Estoque> findByProdutoId(Integer id);
}
