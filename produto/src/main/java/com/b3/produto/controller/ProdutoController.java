package com.b3.produto.controller;




import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Page<Produto>> listar(ProdutosServices produto,
			@RequestParam (required = true) int pagina, 
			@RequestParam (required = true) int itens) throws IOException
	{
		ResponseEntity<Page<Produto>> get = produto.Get(produtoRepository, pagina, itens);
		return get;
	}
	
	@GetMapping("/{id}") // ----Checa se o Produto com o Respectivo id Existe no Banco de Dados, caso não exista, retorna um NOT_FOUND
	public ResponseEntity<Optional<Produto>> listarPorId(@PathVariable Long id, ProdutosServices produto){
		ResponseEntity<Optional<Produto>> pesquisa = produto.GetById(id, produtoRepository);
		return pesquisa;
	}


	@PostMapping // ----Criar um Produto
	public ResponseEntity<Produto> Cadastrar(@RequestBody @Valid Produto produto, ProdutosServices prod){
		ResponseEntity<Produto> p = prod.Post(produtoRepository, produto);
		return p;
	}


	@PatchMapping("/{id}") //----Editar um Produto por ID
	@Transactional
	public ResponseEntity<Produto> Alterar(@PathVariable Long id, @RequestBody @Valid ProdutosServices produto){
		try {
			Produto novo = produto.Atualizar(id, produtoRepository);
			produtoRepository.save(novo);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping("/{id}") //----Deletar um produto por ID
	public ResponseEntity<Produto> deletar(@PathVariable Long id, ProdutosServices produto) {
		ResponseEntity<Produto> deletado = produto.deletar(id,produtoRepository);
		return deletado;
	}
}
