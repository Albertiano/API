package br.com.eiasiscon.financeiro.planocontas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eiasiscon.base.BaseService;

@Service
public class PlanoContasService extends BaseService<PlanoContas, Long> {
	
	@Autowired
	private PlanoContasRepository repository;
	
	@Autowired
	public void setJpaRepository(PlanoContasRepository jpa) {
		setJpa(jpa);
	}
	
	public Page<PlanoContas> find(String query, Long empresa, Pageable pageable) {
		Page<PlanoContas>  entities = repository.find(query, empresa, pageable);
		return entities;
	}
}

