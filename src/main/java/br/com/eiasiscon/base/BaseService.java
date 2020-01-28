package br.com.eiasiscon.base;

import java.io.Serializable;
import java.util.Collection;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<T extends BaseEntity, ID extends Serializable> {
	
	protected JpaRepository<T, ID> jpa;

	public JpaRepository<T, ID> getJpa() {
		return jpa;
	}

	public void setJpa(JpaRepository<T, ID> jpa) {
		this.jpa = jpa;
	}
	
	public T create(T entity) {
		T entitySaved= jpa.save(entity);
		return entitySaved;
	}
	
	public T retrieve(ID id) {
		T entitySaved = jpa.findById(id).get();
		if (entitySaved == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return entitySaved;
	}
	
	public Collection<T> retrieveAll() {
		Collection<T>  entities = jpa.findAll();
		return entities;
	}
	
	public Page<T> findAll(Pageable pageable) {
		Page<T>  entities = jpa.findAll(pageable);
		return entities;
	}	
	
	public T update(ID id, T entity) {
		T entitySaved = retrieve(id);
		BeanUtils.copyProperties(entity, entitySaved, "id");
		return jpa.save(entitySaved);
	}
	
	public void delete(T entity) {		
		jpa.delete(entity);
	}
	
	public void delete(ID id) {		
		jpa.deleteById(id);
	}
	
	public void remove(T entity) {	
		entity.setActive(false);
		jpa.save(entity);
	}
	
	public void activate(ID id, Boolean active) {
		T entitySaved = retrieve(id);
		entitySaved.setActive(active);
		jpa.save(entitySaved);
	}
	
	public Long count() {
		return jpa.count();
	}
}