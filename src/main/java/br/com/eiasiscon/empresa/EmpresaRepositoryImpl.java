package br.com.eiasiscon.empresa;

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

import br.com.eiasiscon.empresa.Empresa;


@Repository
public class EmpresaRepositoryImpl implements EmpresaRepositoryCustom{
	
	private final MongoOperations operations;

	@Autowired
	public EmpresaRepositoryImpl(MongoOperations operations) {

		Assert.notNull(operations, "[Assertion failed] - this argument is required; it must not be null");
		this.operations = operations;
	}

	@Override
	public Page<Empresa> find(String q, String user, Pageable pageable) {
		Query query = new Query();
				
		query.addCriteria(
				Criteria.where("users.id").is(user)
				.orOperator(
							Criteria.where("nome").regex(q,"i"), 
							Criteria.where("contato").regex(q,"i"),
							Criteria.where("fantasia").regex(q,"i"),
							Criteria.where("numDoc").regex(q,"i"),
							Criteria.where("bairro").regex(q,"i")
				)
		);
		
		query.with(pageable);
		
		List<Empresa> list = operations.find(query, Empresa.class);
	    return PageableExecutionUtils.getPage(list, pageable,
	            () -> operations.count(query, Empresa.class));
	}

}
