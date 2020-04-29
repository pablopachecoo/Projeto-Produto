package com.b3.produto.servicos;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.TransientPropertyValueException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.b3.produto.Repository.ProdutoRepository;
import com.b3.produto.model.Categoria;
import com.b3.produto.model.Produto;

public class ProdutosServices {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@NotNull @NotEmpty
	private String Nome;
	@NotNull
	private Integer Quantidade;
	@NotNull
	private Boolean Usado;
	@NotNull @NotEmpty
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

	//Retorna o Produto pelo ID |GET_ID
	public ResponseEntity<Optional<Produto>> GetById(Long id, ProdutoRepository produtoRepository) {   

		if (produtoRepository.existsById(id) != true) {
			System.out.println("o id não existe");
			return new ResponseEntity<Optional<Produto>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Optional<Produto>>(produtoRepository.findById(id), HttpStatus.OK);
	}
	
	//Retorna todos os Produtos |GET
	public ResponseEntity<Page<Produto>> Get(ProdutoRepository produtoRepository, int paginas, int itens) {
		PageRequest paginacao = PageRequest.of(paginas, itens);
		return ResponseEntity.ok(produtoRepository.findAll(paginacao));
	}
	
	// Criar um Produto |POST
	public ResponseEntity<Produto> Post(ProdutoRepository produtoRepository, Produto p) {
			produtoRepository.save(p);
			return ResponseEntity.ok(p);
	}

	public ResponseEntity<Produto> deletar(Long id, ProdutoRepository produtoRepository) {
		produtoRepository.deleteById(id);
		return new ResponseEntity<Produto>(HttpStatus.OK);
	}
}
