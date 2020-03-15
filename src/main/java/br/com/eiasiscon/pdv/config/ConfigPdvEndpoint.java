package br.com.eiasiscon.pdv.config;

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

@CrossOrigin
@RestController
@RequestMapping("/config-pdv")
public class ConfigPdvEndpoint {
	
	@Autowired
	private ConfigPdvService service;
		
	@GetMapping
	public Page<ConfigPdv> procurar(@RequestParam String filter, @RequestParam Long empresa, Pageable pageable) {
		Page<ConfigPdv> contatos =  service.find(filter, empresa, pageable);
		return contatos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ConfigPdv> recuperar(@PathVariable Long id) {
		ConfigPdv entity = service.retrieve(id);
		return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<ConfigPdv> criar(@Valid @RequestBody ConfigPdv entity, HttpServletResponse response) {
		ConfigPdv entitySaved = service.create(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entitySaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ConfigPdv> atualizar(@PathVariable Long id, @Valid @RequestBody ConfigPdv entity) {
		ConfigPdv entitySaved = service.update(id, entity);
		return ResponseEntity.ok(entitySaved);
	}

}
