package br.com.eiasiscon.financeiro.conta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContaRepositoryCustom {	
	
	Page<Conta> find(String q, String empresa, Pageable page);
}
