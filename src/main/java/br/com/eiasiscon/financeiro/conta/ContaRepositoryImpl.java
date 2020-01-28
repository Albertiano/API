package br.com.eiasiscon.financeiro.conta;

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

@Repository
public class ContaRepositoryImpl implements ContaRepositoryCustom{
	
	private final MongoOperations operations;

	@Autowired
	public ContaRepositoryImpl(MongoOperations operations) {

		Assert.notNull(operations, "[Assertion failed] - this argument is required; it must not be null");
		this.operations = operations;
	}

	@Override
	public Page<Conta> find(String q, String empresa, Pageable pageable) {
		Query query = new Query();
		
		query.addCriteria(
				Criteria.where("empresa.id").is(empresa)
				.orOperator(
							Criteria.where("descricao").regex(q,"i")
				)
		);
		
		query.with(pageable);
		
		List<Conta> list = operations.find(query, Conta.class);
		
	    return PageableExecutionUtils.getPage(list, pageable,
	            () -> operations.count(query, Conta.class));
	}

}
