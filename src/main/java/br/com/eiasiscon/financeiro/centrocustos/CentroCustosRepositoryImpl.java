package br.com.eiasiscon.financeiro.centrocustos;

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
public class CentroCustosRepositoryImpl implements CentroCustosRepositoryCustom{
	
	private final MongoOperations operations;

	@Autowired
	public CentroCustosRepositoryImpl(MongoOperations operations) {

		Assert.notNull(operations, "[Assertion failed] - this argument is required; it must not be null");
		this.operations = operations;
	}

	@Override
	public Page<CentroCustos> find(String q, String empresa, Pageable pageable) {
		Query query = new Query();
		
		query.addCriteria(
				Criteria.where("empresa.id").is(empresa)
				.orOperator(
							Criteria.where("descricao").regex(q,"i")
				)
		);
		
		query.with(pageable);
		
		List<CentroCustos> list = operations.find(query, CentroCustos.class);
		
	    return PageableExecutionUtils.getPage(list, pageable,
	            () -> operations.count(query, CentroCustos.class));
	}

}
