package com.b3.produto.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long Id;
	public String Nome;

	public Categoria() {
		
	}
	
	public Categoria(Long id, String nome) {
		Id = id;
		Nome = nome;
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

}
