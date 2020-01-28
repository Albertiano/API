package br.com.eiasiscon.pdv.config;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConfigPdvRepositoryCustom {	
	
	Page<ConfigPdv> find(String q, String empresa, Pageable page);
}
