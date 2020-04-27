package com.b3.produto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.b3.produto.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}