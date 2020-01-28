package br.com.eiasiscon.produto;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.eiasiscon.produto.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String>, ProdutoRepositoryCustom {	

}
