package br.com.eiasiscon.notafiscal;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class NotaFiscalRepositoryImpl implements NotaFiscalRepositoryCustom{
	
	private final MongoOperations operations;

	@Autowired
	public NotaFiscalRepositoryImpl(MongoOperations operations) {

		Assert.notNull(operations, "[Assertion failed] - this argument is required; it must not be null");
		this.operations = operations;
	}

	@Override
	public Page<NotaFiscal> find(String q, String empresa, Pageable pageable) {
		Query query = new Query();
		
		query.addCriteria(
				Criteria.where("empresa.id").is(empresa)
				.orOperator(
							Criteria.where("chave").regex(q,"i"), 
							Criteria.where("numero").regex(q,"i"),
							Criteria.where("dest.nome").regex(q,"i"),
							Criteria.where("dest.numDoc").regex(q,"i")
				)
		);
		
		query.with(pageable);
		query.with(new Sort(Sort.Direction.DESC, "numero"));
		
		List<NotaFiscal> list = operations.find(query, NotaFiscal.class);
		
	    return PageableExecutionUtils.getPage(list, pageable,
	            () -> operations.count(query, NotaFiscal.class));
	}

	@Override
	public Integer maxSerie(String empresa) {
		int last = 0;
		Query query = new Query();		
		query.addCriteria(Criteria.where("empresa.id").is(empresa));
		query.limit(1);
		query.with(new Sort(Sort.Direction.DESC, "serie"));
		NotaFiscal lastNf = operations.findOne(query, NotaFiscal.class);
		if(lastNf != null) last = lastNf.getSerie();
		return last;
	}

	@Override
	public Integer maxNumero(String empresa) {
		int last = 0;
		Query query = new Query();		
		query.addCriteria(Criteria.where("empresa.id").is(empresa));
		query.limit(1);
		query.with(new Sort(Sort.Direction.DESC, "numero"));
		NotaFiscal lastNf = operations.findOne(query, NotaFiscal.class);
		if(lastNf != null) last = lastNf.getNumero();
		return last;
	}
	
	@Override
	public List<NotaFiscal> findByEmissao(Date ini, Date fim, String empresa) {
		Query query = new Query();
		
		query.addCriteria(
				Criteria.where("empresa.id").is(empresa)
				.andOperator(
							Criteria.where("dhEmi").gte(ini).lte(fim)
				)
		);
		
		query.with(new Sort(Sort.Direction.DESC, "numero"));
		
		List<NotaFiscal> list = operations.find(query, NotaFiscal.class);
		
	    return list;
	}

}
