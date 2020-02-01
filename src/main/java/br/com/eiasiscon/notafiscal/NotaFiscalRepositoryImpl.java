package br.com.eiasiscon.notafiscal;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class NotaFiscalRepositoryImpl implements NotaFiscalRepositoryCustom{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	public NotaFiscalRepositoryImpl(EntityManager entityManager) {
		Assert.notNull(entityManager, "[Assertion failed] - this argument is required; it must not be null");
		this.entityManager = entityManager;
	}

	@Override
	public Page<NotaFiscal> find(String q, Long empresa, Pageable pageable) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<NotaFiscal> query = cb.createQuery(NotaFiscal.class);
        Root<NotaFiscal> from = query.from(NotaFiscal.class);
  
		List<Predicate> predicates = new ArrayList<>();
				
		if ((q != null) && (!(q.isEmpty())) && (!q.equalsIgnoreCase("all"))) {
			predicates.add(
				cb.like(
					cb.upper(from.<String> get("numero")), "%" + q.toUpperCase() + "%"
				)
			);
			predicates.add(
				cb.like(
					cb.upper(from.<String> get("chave")), "%" + q.toUpperCase() + "%"
				)
			);
		}

		predicates.add(cb.and(cb.equal(from.get("empresa"), empresa)));
		
		query.select(from)
			.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

		query.orderBy(cb.desc(from.get("numero")));
 
        List<NotaFiscal> result = entityManager.createQuery(query).getResultList();
        
	    return PageableExecutionUtils.getPage(result, pageable,
				() -> result.size());
	}

	@Override
	public Integer maxSerie(Long empresa) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
        Root<NotaFiscal> from = query.from(NotaFiscal.class);
  
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(cb.and(cb.equal(from.get("empresa"), empresa)));
		
		query.select(cb.max(from.get("serie")));
		query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

		TypedQuery<Integer> q = entityManager.createQuery(query);
 
        int result = q.getSingleResult();
		
		return result;
	}

	@Override
	public Integer maxNumero(Long empresa) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
        Root<NotaFiscal> from = query.from(NotaFiscal.class);
  
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(cb.and(cb.equal(from.get("empresa"), empresa)));
		
		query.select(cb.max(from.get("numero")));
		query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

		TypedQuery<Integer> q = entityManager.createQuery(query);
 
        int result = q.getSingleResult();
		
		return result;
	}
	
	@Override
	public List<NotaFiscal> findByEmissao(Date ini, Date fim, Long empresa) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<NotaFiscal> query = cb.createQuery(NotaFiscal.class);
        Root<NotaFiscal> from = query.from(NotaFiscal.class);
  
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(
			cb.and(
				cb.greaterThanOrEqualTo(from.get("dhEmi"), ini),
				cb.lessThanOrEqualTo(from.get("dhEmi"), fim)
				)
			);

		predicates.add(cb.and(cb.equal(from.get("empresa"), empresa)));
		
		query.select(from)
			.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

		query.orderBy(cb.desc(from.get("numero")));
 
        List<NotaFiscal> result = entityManager.createQuery(query).getResultList();
        
	    return result;
	}

}
