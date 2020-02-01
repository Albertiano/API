package br.com.eiasiscon.financeiro.conta;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class ContaRepositoryImpl implements ContaRepositoryCustom{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	public ContaRepositoryImpl(EntityManager entityManager) {
		Assert.notNull(entityManager, "[Assertion failed] - this argument is required; it must not be null");
		this.entityManager = entityManager;
	}

	@Override
	public Page<Conta> find(String q, Long empresa, Pageable pageable) {
 
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Conta> query = cb.createQuery(Conta.class);
        Root<Conta> from = query.from(Conta.class);
  
		List<Predicate> predicates = new ArrayList<>();
				
		if ((q != null) && (!(q.isEmpty())) && (!q.equalsIgnoreCase("all"))) {
			predicates.add(
				cb.like(
					cb.upper(from.<String> get("descricao")),
					"%" + q.toUpperCase() + "%"));
		}

		predicates.add(cb.and(cb.equal(from.get("empresa"), empresa)));
		
		query.select(from)
			.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

		query.orderBy(cb.asc(from.get("descricao")));
 
        List<Conta> result = entityManager.createQuery(query).getResultList();
        
	    return PageableExecutionUtils.getPage(result, pageable,
	            () -> result.size());
	}

}
