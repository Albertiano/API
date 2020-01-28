package br.com.eiasiscon.security.user.endpoint;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eiasiscon.security.user.entity.Role;
import br.com.eiasiscon.security.user.service.RoleService;

@CrossOrigin
@RestController
@RequestMapping("/role")
public class RoleEndpoint {
	
	@Autowired
	private RoleService service;
		
	@GetMapping
	public Page<Role> search(Pageable pageable) {
		Page<Role> contatos =  service.findAll(pageable);
		return contatos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Role> retrieve(@PathVariable String id) {
		Role entity = service.retrieve(id);
		return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Role> create(@Valid @RequestBody Role entity, HttpServletResponse response) {
		Role entitySaved = service.create(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entitySaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable String id) {
		service.activate(id, false);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Role> update(@PathVariable String id, @Valid @RequestBody Role entity) {
		Role entitySaved = service.update(id, entity);
		return ResponseEntity.ok(entitySaved);
	}

}
