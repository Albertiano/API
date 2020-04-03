package br.com.eiasiscon.financeiro.centrocustos;

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
@RequestMapping("/centro-custos")
public class CentroCustosEndpoint extends BaseEndpoint<CentroCusto, Long> {
	
	@Autowired
	private CentroCustosService service;

	@Autowired
	public void setBaseService(CentroCustosService service) {
		setService(service);
	}
		
	@GetMapping("/filter")
	public Page<CentroCusto> procurar(@RequestParam String filter, @RequestParam Long idCompany, Pageable pageable) {
		Page<CentroCusto> result =  service.find(filter, idCompany, pageable);
		return result;
	}

}
