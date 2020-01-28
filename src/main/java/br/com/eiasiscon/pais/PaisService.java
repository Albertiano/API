package br.com.eiasiscon.pais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eiasiscon.base.BaseService;
import br.com.eiasiscon.pais.Pais;
import br.com.eiasiscon.pais.PaisRepository;

@Service
public class PaisService extends BaseService<Pais, Long> {
	
	@Autowired
	public void setJpaRepository(PaisRepository jpa) {
		setJpa(jpa);
	}
}