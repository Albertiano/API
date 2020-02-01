package br.com.eiasiscon.financeiro.planocontas;

import java.util.Arrays;
import java.util.List;
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
@RequestMapping("/plano-contas")
public class PlanoContasEndpoint extends BaseEndpoint<PlanoContas, Long> {
	
	@Autowired
	private PlanoContasService service;
		
	@GetMapping("/filter")
	public Page<PlanoContas> procurar(@RequestParam String filter, @RequestParam Long idCompany, Pageable pageable) {
		Page<PlanoContas> result =  service.find(filter, idCompany, pageable);
		return result;
	}
	
	@GetMapping("/TpConta")
	public List<TpConta> getFinNFe() {
		List<TpConta> res = Arrays.asList(TpConta.values());
		return res;
	}

}
