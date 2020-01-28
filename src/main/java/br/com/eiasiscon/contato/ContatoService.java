package br.com.eiasiscon.contato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eiasiscon.base.BaseService;
import br.com.eiasiscon.contato.Contato;
import br.com.eiasiscon.contato.ContatoRepository;

@Service
public class ContatoService extends BaseService<Contato, String> {
	
	@Autowired
	ContatoRepository repository;
	
	@Autowired
	public void setJpaRepository(ContatoRepository jpa) {
		setJpa(jpa);
	}
	
	public Page<Contato> find(String query, String empresa, Pageable pageable) {
		Page<Contato>  entities = repository.find(query, empresa, pageable);
		return entities;
	}
}
