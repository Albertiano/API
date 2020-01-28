package br.com.eiasiscon.financeiro.planocontas;

import java.util.Arrays;
import java.util.List;

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
@RequestMapping("/plano-contas")
public class PlanoContasEndpoint {
	
	@Autowired
	private PlanoContasService service;
		
	@GetMapping
	public Page<PlanoContas> procurar(@RequestParam String filter, @RequestParam String empresa, Pageable pageable) {
		Page<PlanoContas> contatos =  service.find(filter, empresa, pageable);
		return contatos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PlanoContas> recuperar(@PathVariable String id) {
		PlanoContas entity = service.retrieve(id);
		return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<PlanoContas> criar(@Valid @RequestBody PlanoContas entity, HttpServletResponse response) {
		PlanoContas entitySaved = service.create(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entitySaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable String id) {
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PlanoContas> atualizar(@PathVariable String id, @Valid @RequestBody PlanoContas entity) {
		PlanoContas entitySaved = service.update(id, entity);
		return ResponseEntity.ok(entitySaved);
	}
	
	@GetMapping("/TpConta")
	public List<TpConta> getFinNFe() {
		List<TpConta> res = Arrays.asList(TpConta.values());
		return res;
	}

}
