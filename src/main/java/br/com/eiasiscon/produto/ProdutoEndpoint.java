package br.com.eiasiscon.produto;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eiasiscon.produto.Produto;
import br.com.eiasiscon.produto.ProdutoService;

@CrossOrigin
@RestController
@RequestMapping("/produto")
public class ProdutoEndpoint {
	
	@Autowired
	private ProdutoService service;
		
	@GetMapping
	public Page<Produto> procurar(@RequestParam String filter, @RequestParam String empresa, Pageable pageable) {
		Page<Produto> contatos =  service.find(filter, empresa, pageable);
		return contatos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> recuperar(@PathVariable String id) {
		Produto entity = service.retrieve(id);
		return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Produto> criar(@Valid @RequestBody Produto entity, HttpServletResponse response) {
		if(entity.getCodigo() == null || entity.getCodigo().isEmpty()) {
			entity.setCodigo(UUID.randomUUID().toString());
		}
		Produto entitySaved = service.create(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entitySaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable String id) {
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable String id, @Valid @RequestBody Produto entity) {
		Produto entitySaved = service.update(id, entity);
		return ResponseEntity.ok(entitySaved);
	}

}