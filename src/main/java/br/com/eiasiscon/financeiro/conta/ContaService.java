package br.com.eiasiscon.financeiro.conta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eiasiscon.base.BaseService;

@Service
public class ContaService extends BaseService<Conta, String> {
	
	@Autowired
	private ContaRepository repository;
	
	@Autowired
	public void setJpaRepository(ContaRepository jpa) {
		setJpa(jpa);
	}
	
	public Page<Conta> find(String query, String empresa, Pageable pageable) {
		Page<Conta>  entities = repository.find(query, empresa, pageable);
		return entities;
	}
}

