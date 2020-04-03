package br.com.eiasiscon.financeiro.centrocustos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CentroCustosRepositoryCustom {	
	
	Page<CentroCusto> find(String q, Long empresa, Pageable page);
}
