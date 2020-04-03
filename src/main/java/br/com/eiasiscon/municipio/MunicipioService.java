package br.com.eiasiscon.municipio;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eiasiscon.base.BaseService;
import br.com.eiasiscon.municipio.Municipio;

@Service
public class MunicipioService  extends BaseService<Municipio, Long>{
	
	@Autowired
	MunicipioRepository repository;
	
	@Autowired
	public void setJpaRepository(MunicipioRepository jpa) {
		setJpa(jpa);
	}
	
	public Collection<Municipio> retrieveAll(UF uf) {
		Collection<Municipio>  entities = repository.findByUf(uf);
		return entities;
	}
}
