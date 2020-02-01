package br.com.eiasiscon.financeiro.conta;

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
@RequestMapping("/conta")
public class ContaEndpoint extends BaseEndpoint<Conta, Long> {
	
	@Autowired
	private ContaService service;

	@Autowired
	public void setBaseService(ContaService service) {
		setService(service);
	}
		
	@GetMapping("/filter")
	public Page<Conta> procurar(@RequestParam String filter, @RequestParam Long idCompany, Pageable pageable) {
		Page<Conta> result =  service.find(filter, idCompany, pageable);
		return result;
	}
}
