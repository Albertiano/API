package br.com.eiasiscon.configuracao;

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
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class ConfiguracaoRepositoryImpl implements ConfiguracaoRepositoryCustom{
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	public ConfiguracaoRepositoryImpl(EntityManager entityManager) {
		Assert.notNull(entityManager, "[Assertion failed] - this argument is required; it must not be null");
		this.entityManager = entityManager;
	}
	
	@Override
	public Configuracao getConfiguracao(Long empresa) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Configuracao> query = cb.createQuery(Configuracao.class);
        Root<Configuracao> from = query.from(Configuracao.class);
  
		List<Predicate> predicates = new ArrayList<>();
		
        predicates.add(cb.equal(from.get("empresa"), empresa));		
		
        query.select(from)
			.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

		query.orderBy(cb.asc(from.get("id")));
			
		TypedQuery<Configuracao> q = entityManager.createQuery(query);
			
		Configuracao results = q.getSingleResult();
       
        return results;
	}
	
}
