package br.com.eiasiscon.base;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class BaseEndpoint<T extends BaseEntity, ID extends Serializable> {

    protected BaseService<T, ID> service;
	
    public void setService(BaseService<T, ID> service) {
        this.service = service;
    };
    
    @GetMapping
	public Page<T> findAll(Pageable pageable) {
		Page<T> contatos =  service.findAll(pageable);
		return contatos;
    }
    
    @GetMapping("/{id}")
	public ResponseEntity<T> retrieve(@PathVariable ID id) {
		T entity = service.retrieve(id);
		return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
	public ResponseEntity<T> create(@Valid @RequestBody T entity, HttpServletResponse response) {
		T entitySaved = service.create(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entitySaved);
    }
    
    @PutMapping("/{id}")
	public ResponseEntity<T> atualizar(@PathVariable ID id, @Valid @RequestBody T entity) {
		T entitySaved = service.update(id, entity);
		return ResponseEntity.ok(entitySaved);
    }
    
    @DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable ID id) {
		service.delete(id);
	}

}