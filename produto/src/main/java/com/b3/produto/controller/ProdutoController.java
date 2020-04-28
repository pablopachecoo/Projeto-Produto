package com.b3.produto.controller;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.TransientPropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	
	@GetMapping // ----Ver Lista de Produtos, se não existe nenhum produto, é retornado um NOT_FOUND
	public ResponseEntity<List<Produto>> listar(){
		if (produtoRepository.count() != 0) {
			return ResponseEntity.ok(produtoRepository.findAll());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}") // ----Checa se o Produto com o Respectivo id Existe no Banco de Dados, caso não exista, retorna um NOT_FOUND
	public ResponseEntity<Optional<Produto>> listarPorId(@PathVariable Long id){
		
		if (produtoRepository.existsById(id) != true) {
			System.out.println("o id não existe");
			return new ResponseEntity<Optional<Produto>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Optional<Produto>>(produtoRepository.findById(id),HttpStatus.OK);
		
	}
	
	@PostMapping // ----Criar um Produto
	public ResponseEntity<Produto> Cadastrar(@RequestBody @Valid Produto produto){
		try {
			produtoRepository.save(produto);
		} catch (TransientPropertyValueException | InvalidDataAccessApiUsageException e) {
			return new ResponseEntity<Produto>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("CHEGOU NO FINAL");
		return null;
		
	}
		
	
	@PatchMapping("/{id}") //----Editar um Produto por ID
	@Transactional
	public ResponseEntity<Produto> Alterar(@PathVariable Long id, @RequestBody @Valid ProdutosServices produto){
		try {
			Produto novo = produto.Atualizar(id, produtoRepository);
			produtoRepository.save(novo);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "entrou no catch");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}") //----Deletar um produto por ID
	public void deletar(@PathVariable Long id) {
		Produto deletado = produtoRepository.getOne(id);
		produtoRepository.delete(deletado);
	}
}
