package br.com.eiasiscon.empresa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.eiasiscon.empresa.Empresa;

public interface EmpresaRepositoryCustom{	

	Page<Empresa> find(String q, String user, Pageable page);

}
