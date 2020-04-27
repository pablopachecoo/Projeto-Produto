package com.b3.produto.servicos;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.b3.produto.Repository.ProdutoRepository;
import com.b3.produto.model.Categoria;
import com.b3.produto.model.Produto;

public class EditarProdutos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String Nome;
	private Integer Quantidade;
	private Boolean Usado;
	private String Descricao;
	@ManyToOne
	public Categoria categoria;

	public EditarProdutos() {
		// TODO Auto-generated constructor stub
	}

	public EditarProdutos(Long id, String nome, Integer quantidade, Boolean usado, String descricao, Categoria categoria) {
		Id = id;
		Nome = nome;
		Quantidade = quantidade;
		Usado = usado;
		Descricao = descricao;
		this.categoria = categoria;
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
