package br.com.eiasiscon.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eiasiscon.base.BaseService;
import br.com.eiasiscon.produto.Produto;
import br.com.eiasiscon.produto.ProdutoRepository;
@Service
public class ProdutoService extends BaseService<Produto, String> {
	
	@Autowired
	ProdutoRepository repository;
	
	@Autowired
	public void setJpaRepository(ProdutoRepository jpa) {
		setJpa(jpa);
	}
	
	public Page<Produto> find(String query, String empresa, Pageable pageable) {
		Page<Produto>  entities = repository.find(query, empresa, pageable);
		return entities;
	}
}
