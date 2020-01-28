package br.com.eiasiscon.empresa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

import br.com.eiasiscon.empresa.CRT;
import br.com.eiasiscon.empresa.Empresa;
import br.com.eiasiscon.empresa.EmpresaService;
import br.com.eiasiscon.security.auth.JwtAuthenticationToken;
import br.com.eiasiscon.security.model.UserContext;
import br.com.eiasiscon.security.user.entity.User;
import br.com.eiasiscon.security.user.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/empresa")
public class EmpresaEndpoint {
	
	@Autowired
	private EmpresaService service;
	@Autowired
	private UserService userService;
		
	@GetMapping
	public Page<Empresa> procurar(JwtAuthenticationToken token, @RequestParam String filter, Pageable pageable) {
		UserContext principal = (UserContext) token.getPrincipal();
		Optional<User> user = userService.getByUsername(principal.getUsername());
		
		Page<Empresa> registros =  service.find(filter, user.get().getId(), pageable);
		
		return registros;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Empresa> recuperar(@PathVariable String id) {
		Empresa entity = service.retrieve(id);
		return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/crts")
	public List<CRT> getCRTs() {
		List<CRT> ufs = Arrays.asList(CRT.values());
		return ufs;
	}
	
	@PostMapping
	public ResponseEntity<Empresa> criar(@Valid @RequestBody Empresa entity, HttpServletResponse response) {
		Empresa entitySaved = service.create(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entitySaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable String id) {
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Empresa> atualizar(@PathVariable String id, @Valid @RequestBody Empresa entity) {
		Empresa entitySaved = service.update(id, entity);
		return ResponseEntity.ok(entitySaved);
	}

}