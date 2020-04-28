package com.b3.produto.servicos;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.b3.produto.Repository.ProdutoRepository;
import com.b3.produto.model.Categoria;
import com.b3.produto.model.Produto;

public class ProdutosServices {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@NotNull
	private String Nome;
	@NotNull
	private Integer Quantidade;
	@NotNull
	private Boolean Usado;
	@NotNull
	private String Descricao;
	@ManyToOne
	@NotNull
	public Categoria categoria;

	public ProdutosServices() {

	}

	public ProdutosServices(Produto produto) {
		this.Id = produto.getId();
		this.Nome = produto.getNome();
		this.Quantidade = produto.getQuantidade();
		this.Usado = produto.getUsado();
		this.Descricao = produto.getDescricao();
		this.categoria = produto.getCategoria();
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public Integer getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		Quantidade = quantidade;
	}

	public Boolean getUsado() {
		return Usado;
	}

	public void setUsado(Boolean usado) {
		Usado = usado;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public Produto Atualizar(Long id, ProdutoRepository produtoRepository) {
		
		Produto produto = produtoRepository.getOne(id);
		produto.setNome(this.getNome());
		produto.setQuantidade(this.getQuantidade());
		produto.setUsado(this.getUsado());
		produto.setDescricao(this.getDescricao());
		produto.setCategoria(this.getCategoria());
		return produto;
		
	}

}
