package com.b3.produto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.b3.produto.servicos.ProdutosServices;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	@GetMapping // ----Ver lista de Produtos
	public List<Produto> listar(){
		List<Produto> produto = produtoRepository.findAll();
		return produto;
	}
	
	@GetMapping("/{id}") // ----Ver Produto por ID
	@Transactional
	public Optional<Produto> listarPorId(@PathVariable Long id){
		return produtoRepository.findById(id);
	}
	
	@PostMapping // ----Criar um Produto
	public void Cadastrar(@RequestBody Produto produto){
		produtoRepository.save(produto);
	}
	
	
	@PatchMapping("/{id}")
	@Transactional
	public void Alterar(@PathVariable Long id, @RequestBody ProdutosServices produto){
		Produto novo = produto.Atualizar(id, produtoRepository);
		produtoRepository.save(novo);
		
		//alterado.setDescricao(produto.getDescricao());
		//produtoRepository.save(alterado);
	}
}
