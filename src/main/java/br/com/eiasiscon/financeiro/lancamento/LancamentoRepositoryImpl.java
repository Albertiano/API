package br.com.eiasiscon.financeiro.lancamento;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class LancamentoRepositoryImpl implements LancamentoRepositoryCustom{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	public LancamentoRepositoryImpl(EntityManager entityManager) {
		Assert.notNull(entityManager, "[Assertion failed] - this argument is required; it must not be null");
		this.entityManager = entityManager;
	}

	@Override
	public Page<Lancamento> find(LancamentoFiltro filtro, Pageable pageable) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> query = cb.createQuery(Lancamento.class);
        Root<Lancamento> from = query.from(Lancamento.class);
  
		List<Predicate> predicates = new ArrayList<>();
				
		if ((filtro.getQ() != null) && (!(filtro.getQ().isEmpty())) && (!filtro.getQ().equalsIgnoreCase("all"))) {
			predicates.add(
				cb.like(
					cb.upper(from.<String> get("descricao")),
					"%" + filtro.getQ().toUpperCase() + "%"));
		}

		predicates.add(
			cb.and(
				cb.greaterThanOrEqualTo(from.get("competencia"), filtro.getIni()),
				cb.lessThanOrEqualTo(from.get("competencia"), filtro.getFim())
				)
			);

		predicates.add(cb.and(cb.equal(from.get("empresa"), filtro.getIdEmpresa())));
		
		query.select(from)
			.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

		query.orderBy(cb.asc(from.get("descricao")));
 
        List<Lancamento> result = entityManager.createQuery(query).getResultList();
        
	    return PageableExecutionUtils.getPage(result, pageable,
				() -> result.size());
	}
	
	@Override
	public Lancamento ultimo(Long empresa) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> query = cb.createQuery(Lancamento.class);
        Root<Lancamento> from = query.from(Lancamento.class);
  
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(cb.and(cb.equal(from.get("empresa"), empresa)));
		
		query.select(from)
			.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

		query.orderBy(cb.desc(from.get("createdAt")));

		TypedQuery<Lancamento> q = entityManager.createQuery(query).setMaxResults(1);
 
        Lancamento result = q.getSingleResult();;
		
		return result;
	}
	
	@Override
	public int totalPages(LancamentoFiltro filtro, Pageable pageable) {
		
		Page<Lancamento> page = find(filtro, pageable);

	    return page.getTotalPages();
	}

}
