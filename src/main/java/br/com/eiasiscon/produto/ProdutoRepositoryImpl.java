package br.com.eiasiscon.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import br.com.eiasiscon.produto.Produto;


@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryCustom{
	
	private final MongoOperations operations;

	@Autowired
	public ProdutoRepositoryImpl(MongoOperations operations) {

		Assert.notNull(operations, "[Assertion failed] - this argument is required; it must not be null");
		this.operations = operations;
	}

	@Override
	public Page<Produto> find(String q, String empresa, Pageable pageable) {
		Query query = new Query();
				
		query.addCriteria(
				Criteria.where("empresa.id").is(empresa)
				.orOperator(
						Criteria.where("codigo").regex(q,"i"), 
						Criteria.where("referencia").regex(q,"i"),
						Criteria.where("descricao").regex(q,"i"),
						Criteria.where("ncm").regex(q,"i"),
						Criteria.where("localizacao").regex(q,"i")
				)
		);
		
		query.with(pageable);
		
		List<Produto> list = operations.find(query, Produto.class);
		
	    return PageableExecutionUtils.getPage(list, pageable,
	            () -> operations.count(query, Produto.class));
	}

}
