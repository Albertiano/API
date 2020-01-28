package br.com.eiasiscon.configuracao;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/configuracao")
public class ConfiguracaoEndpoint {
	
	@Autowired
	private ConfiguracaoService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Configuracao> recuperar(@PathVariable String id) {
		Configuracao entity = service.retrieve(id);
		return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Configuracao> criar(@Valid @RequestBody Configuracao entity, HttpServletResponse response) {
		Configuracao entitySaved = service.create(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entitySaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable String id) {
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Configuracao> atualizar(@PathVariable String id, @Valid @RequestBody Configuracao entity) {
		Configuracao entitySaved = service.update(id, entity);
		return ResponseEntity.ok(entitySaved);
	}
	
	@GetMapping("/empresa={empresa}")
	public ResponseEntity<Configuracao> get(@PathVariable String empresa) {
		Configuracao entity = service.getConfiguracao(empresa);
		return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}

}
