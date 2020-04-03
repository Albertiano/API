package br.com.eiasiscon.municipio;

import java.util.Arrays;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/municipio")
public class MunicipioEndpoint {
	
	@Autowired
	private MunicipioService service;
	
	@GetMapping
	public List<Municipio> listarPorUF(@RequestParam UF uf) {
		List<Municipio> contatos = (List<Municipio>) service.retrieveAll(uf);
		return contatos;
	}
	
	@GetMapping("/ufs")
	public List<UF> getUFs() {
		List<UF> ufs = Arrays.asList(UF.values());
		return ufs;
	}
	
	@PostMapping
	public ResponseEntity<Municipio> criar(@Valid @RequestBody Municipio entity, HttpServletResponse response) {
		Municipio entitySaved = service.create(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entitySaved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Municipio> buscarPeloCodigo(@PathVariable Long id) {
		Municipio entity = service.retrieve(id);
		return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Municipio> atualizar(@PathVariable Long id, @Valid @RequestBody Municipio entity) {
		Municipio entitySaved = service.update(id, entity);
		return ResponseEntity.ok(entitySaved);
	}
}
