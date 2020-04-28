package com.b3.produto.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Produto {
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

	public Produto() {

	}

	public Produto(Long id, String nome, Integer quantidade, Boolean usado, String descricao, Categoria categoria) {
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


}
