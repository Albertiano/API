package br.com.eiasiscon.pais;

import java.util.List;
import javax.validation.Valid;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/paises")
public class PaisEndPoint {
    
    @Autowired
    private PaisService service;
    
    @GetMapping("/test")
	public List<Pais> test() {
        service.create(new Pais(11, "Pais1"));
		service.create(new Pais(12, "Pais2"));
		service.create(new Pais(13, "Pais3"));

        System.out.println("\nfindAll()");
        service.retrieveAll().forEach(x -> System.out.println(x));

		List<Pais> contatos = (List<Pais>) service.retrieveAll();
		return contatos;
	}
		
	@GetMapping
	public List<Pais> listar() {
		List<Pais> contatos = (List<Pais>) service.retrieveAll();
		return contatos;
	}
	
	@PostMapping
	public ResponseEntity<Pais> criar(@Valid @RequestBody Pais entity, HttpServletResponse response) {
		Pais entitySaved = service.create(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entitySaved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pais> buscarPeloCodigo(@PathVariable Long id) {
		Pais entity = service.retrieve(id);
		return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pais> atualizar(@PathVariable Long id, @Valid @RequestBody Pais entity) {
		Pais entitySaved = service.update(id, entity);
		return ResponseEntity.ok(entitySaved);
	}
	
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		service.activate(id, ativo);
	}
}