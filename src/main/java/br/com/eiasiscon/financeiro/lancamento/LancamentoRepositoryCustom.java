package br.com.eiasiscon.financeiro.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryCustom {	
	
	Page<Lancamento> find(LancamentoFiltro filtro, Pageable page);
	Lancamento ultimo(String empresa);
	int totalPages(LancamentoFiltro filtro, Pageable pageable);
}
