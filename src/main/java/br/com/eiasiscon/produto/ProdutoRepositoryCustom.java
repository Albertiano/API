package br.com.eiasiscon.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.eiasiscon.produto.Produto;

public interface ProdutoRepositoryCustom{	

	Page<Produto> find(String q, Long empresa, Pageable page);

}
