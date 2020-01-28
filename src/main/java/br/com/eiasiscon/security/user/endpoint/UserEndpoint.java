package br.com.eiasiscon.security.user.endpoint;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import br.com.eiasiscon.security.user.entity.User;
import br.com.eiasiscon.security.user.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserEndpoint {

	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService service;
		
	@GetMapping
	public Page<User> search(@RequestParam String filter, Pageable pageable) {
		Page<User> contatos =  service.find(filter, pageable);
		return contatos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> retrieve(@PathVariable String id) {
		User entity = service.retrieve(id);
		return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<User> create(@Valid @RequestBody User entity, HttpServletResponse response) {
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		User entitySaved = service.create(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entitySaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable String id) {
		service.activate(id, false);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable String id, @Valid @RequestBody User entity) {
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		User entitySaved = service.update(id, entity);
		return ResponseEntity.ok(entitySaved);
	}

}
