package br.com.eiasiscon.financeiro.lancamento;

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
public class LancamentoRepositoryImpl implements LancamentoRepositoryCustom{
	
	private final MongoOperations operations;

	@Autowired
	public LancamentoRepositoryImpl(MongoOperations operations) {

		Assert.notNull(operations, "[Assertion failed] - this argument is required; it must not be null");
		this.operations = operations;
	}

	@Override
	public Page<Lancamento> find(LancamentoFiltro filtro, Pageable pageable) {
		Query query = new Query();
		
		query.addCriteria(
				Criteria.where("empresa.id").is(filtro.getIdEmpresa())
				.andOperator(
						Criteria.where("competencia").gte(filtro.getIni()).lte(filtro.getFim())
				)
				.orOperator(
							Criteria.where("descricao").regex(filtro.getQ(),"i")
				)
		);
		
		query.with(pageable);
		
		query.fields().exclude("empresa").exclude("contato.empresa").exclude("conta.empresa").exclude("planoContas.empresa").exclude("centroCustos.empresa");
		
		List<Lancamento> list = operations.find(query, Lancamento.class);
		
	    return PageableExecutionUtils.getPage(list, pageable,
	            () -> operations.count(query, Lancamento.class));
	}
	
	@Override
	public Lancamento ultimo(String empresa) {
		Query query = new Query();		
		query.addCriteria(Criteria.where("empresa.id").is(empresa));
		query.limit(1);
		query.with(new Sort(Sort.Direction.DESC, "createdAt"));
		Lancamento last = operations.findOne(query, Lancamento.class);
		
		return last;
	}
	
	@Override
	public int totalPages(LancamentoFiltro filtro, Pageable pageable) {
		Query query = new Query();
		
		query.addCriteria(
				Criteria.where("empresa.id").is(filtro.getIdEmpresa())
				.andOperator(
						Criteria.where("competencia").gte(filtro.getIni()).lte(filtro.getFim())
				)
				.orOperator(
							Criteria.where("descricao").regex(filtro.getQ(),"i")
				)
		);
		
		query.with(pageable);
		
		List<Lancamento> list = operations.find(query, Lancamento.class);
		
		Page<Lancamento> page = PageableExecutionUtils.getPage(list, pageable,
	            () -> operations.count(query, Lancamento.class));
		
		
		
	    return page.getTotalPages();
	}

}
