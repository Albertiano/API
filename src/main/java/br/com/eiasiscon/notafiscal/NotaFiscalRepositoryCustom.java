package br.com.eiasiscon.notafiscal;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotaFiscalRepositoryCustom {	
	
	Page<NotaFiscal> find(String q, String empresa, Pageable page);
	
	Integer maxSerie(String empresa);
	
	Integer maxNumero(String empresa);

	List<NotaFiscal> findByEmissao(Date ini, Date fim, String empresa);
}
