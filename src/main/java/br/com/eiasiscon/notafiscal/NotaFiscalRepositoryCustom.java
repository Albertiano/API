package br.com.eiasiscon.notafiscal;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotaFiscalRepositoryCustom {	
	
	Page<NotaFiscal> find(String q, Long empresa, Pageable page);
	
	Integer maxSerie(Long empresa);
	
	Integer maxNumero(Long empresa);

	List<NotaFiscal> findByEmissao(Date ini, Date fim, Long empresa);
}
