package br.com.eiasiscon.produto.tributacao;

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

import br.com.eiasiscon.produto.tributacao.Tributacao;
import br.com.eiasiscon.produto.unidade.Unidade;

@Repository
public class TributacaoRepositoryImpl implements TributacaoRepositoryCustom {
	
	private final MongoOperations operations;

	@Autowired
	public TributacaoRepositoryImpl(MongoOperations operations) {

		Assert.notNull(operations, "[Assertion failed] - this argument is required; it must not be null");
		this.operations = operations;
	}

	@Override
	public Page<Tributacao> find(String q, String empresa, Pageable pageable) {
		Query query = new Query();
		
		query.addCriteria(
				Criteria.where("empresa.id").is(empresa)
				.orOperator(
							Criteria.where("nome").regex(q,"i"), 
							Criteria.where("descricao").regex(q,"i")
				)
		);
		
		query.with(pageable);
		
		List<Tributacao> list = operations.find(query, Tributacao.class);
		
	    return PageableExecutionUtils.getPage(list, pageable,
	            () -> operations.count(query, Unidade.class));
	}

}
