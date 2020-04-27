package com.b3.produto.controller;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.b3.produto.Repository.ProdutoRepository;
import com.b3.produto.model.Produto;
import com.b3.produto.servicos.EditarProdutos;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping // Ver lista de Produtos
	public List<Produto> lista(){
		List<Produto> produto = produtoRepository.findAll();
		return produto;
	}
		
	@PostMapping // Criar um Produto
	public void Cadastrar(@RequestBody Produto produto){
		produtoRepository.save(produto);
	}
	
	@PatchMapping("/{id}")
	@PostMapping // Criar um Produto
	@Transactional
	public void Alterar(@PathVariable Long id, @RequestBody EditarProdutos produto){
		Produto novo = produto.Atualizar(id, produtoRepository);
		produtoRepository.save(novo);
		
		//alterado.setDescricao(produto.getDescricao());
		//produtoRepository.save(alterado);
	}
}
