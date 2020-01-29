package br.com.eiasiscon.financeiro.centrocustos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eiasiscon.base.BaseService;

@Service
public class CentroCustosService extends BaseService<CentroCustos, Long> {
	
	@Autowired
	private CentroCustosRepository repository;
	
	@Autowired
	public void setJpaRepository(CentroCustosRepository jpa) {
		setJpa(jpa);
	}
	
	public Page<CentroCustos> find(String query, Long empresa, Pageable pageable) {
		Page<CentroCustos>  entities = repository.find(query, empresa, pageable);
		return entities;
	}
}

