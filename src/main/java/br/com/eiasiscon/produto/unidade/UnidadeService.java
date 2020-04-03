package br.com.eiasiscon.produto.unidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eiasiscon.base.BaseService;
import br.com.eiasiscon.produto.unidade.Unidade;

@Service
public class UnidadeService extends BaseService<Unidade, String> {
	
	@Autowired
	private UnidadeRepository repository;
	
	@Autowired
	public void setJpaRepository(UnidadeRepository jpa) {
		setJpa(jpa);
	}
	
	public Page<Unidade> find(String query, Long empresa, Pageable pageable) {
		Page<Unidade>  entities = repository.find(query, empresa, pageable);
		return entities;
	}
}

