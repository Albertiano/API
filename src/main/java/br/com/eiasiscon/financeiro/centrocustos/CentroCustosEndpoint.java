package br.com.eiasiscon.financeiro.centrocustos;

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
@RequestMapping("/centro-custos")
public class CentroCustosEndpoint {
	
	@Autowired
	private CentroCustosService service;
		
	@GetMapping
	public Page<CentroCustos> procurar(@RequestParam String filter, @RequestParam String empresa, Pageable pageable) {
		Page<CentroCustos> contatos =  service.find(filter, empresa, pageable);
		return contatos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CentroCustos> recuperar(@PathVariable String id) {
		CentroCustos entity = service.retrieve(id);
		return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<CentroCustos> criar(@Valid @RequestBody CentroCustos entity, HttpServletResponse response) {
		CentroCustos entitySaved = service.create(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entitySaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable String id) {
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CentroCustos> atualizar(@PathVariable String id, @Valid @RequestBody CentroCustos entity) {
		CentroCustos entitySaved = service.update(id, entity);
		return ResponseEntity.ok(entitySaved);
	}

}
