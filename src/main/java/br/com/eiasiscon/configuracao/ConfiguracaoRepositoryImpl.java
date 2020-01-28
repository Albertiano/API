package br.com.eiasiscon.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class ConfiguracaoRepositoryImpl implements ConfiguracaoRepositoryCustom{
	
	private final MongoOperations operations;
	
	@Autowired
	public ConfiguracaoRepositoryImpl(MongoOperations operations) {

		Assert.notNull(operations, "[Assertion failed] - this argument is required; it must not be null");
		this.operations = operations;
	}
	
	@Override
	public Configuracao getConfiguracao(String empresa) {
		Query query = new Query();		
		query.addCriteria(Criteria.where("empresa.id").is(empresa));
		query.limit(1);
		query.with(new Sort(Sort.Direction.DESC, "updatedAt"));
			
		Configuracao lastNf = operations.findOne(query, Configuracao.class);
		return lastNf;
	}
	
}
