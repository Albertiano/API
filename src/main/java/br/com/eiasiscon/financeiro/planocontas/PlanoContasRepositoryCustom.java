package br.com.eiasiscon.financeiro.planocontas;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlanoContasRepositoryCustom {	
	
	Page<PlanoContas> find(String q, String empresa, Pageable page);
}
