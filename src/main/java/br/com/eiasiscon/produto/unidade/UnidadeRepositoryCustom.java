package br.com.eiasiscon.produto.unidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UnidadeRepositoryCustom {	
	
	Page<Unidade> find(String q, String empresa, Pageable page);
}
