package br.com.eiasiscon.contato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.eiasiscon.contato.Contato;

public interface ContatoRepositoryCustom {	
	
	Page<Contato> find(String q, String empresa, Pageable page);
}
