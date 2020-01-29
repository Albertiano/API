package br.com.eiasiscon.contato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eiasiscon.base.BaseEndpoint;

@CrossOrigin
@RestController
@RequestMapping("/contato")
public class ContatoEndpoint extends BaseEndpoint<Contato, Long> {
	
	@Autowired
	private ContatoService service;

	@Autowired
	public void setBaseService(ContatoService service) {
		setService(service);
	}
		
	@GetMapping("/filter")
	public Page<Contato> procurar(@RequestParam String filter, @RequestParam Long idCompany, Pageable pageable) {
		Page<Contato> result =  service.find(filter, idCompany, pageable);
		return result;
	}

}
