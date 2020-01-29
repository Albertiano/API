package br.com.eiasiscon.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eiasiscon.base.BaseService;
import br.com.eiasiscon.empresa.Empresa;
import br.com.eiasiscon.empresa.EmpresaRepository;

@Service
public class EmpresaService extends BaseService<Empresa, Long> {
	
	@Autowired
	EmpresaRepository repository;
	
	@Autowired
	public void setJpaRepository(EmpresaRepository jpa) {
		setJpa(jpa);
	}
	
	public Page<Empresa> find(String query, Long user, Pageable pageable) {
		Page<Empresa>  entities = repository.find(query, user, pageable);
		return entities;
	}
}
