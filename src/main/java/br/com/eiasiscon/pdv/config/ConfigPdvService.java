package br.com.eiasiscon.pdv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eiasiscon.base.BaseService;

@Service
public class ConfigPdvService extends BaseService<ConfigPdv, Long> {
	
	@Autowired
	private ConfigPdvRepository repository;
	
	@Autowired
	public void setJpaRepository(ConfigPdvRepository jpa) {
		setJpa(jpa);
	}
	
	public Page<ConfigPdv> find(String query, Long empresa, Pageable pageable) {
		Page<ConfigPdv>  entities = repository.find(query, empresa, pageable);
		return entities;
	}
}

