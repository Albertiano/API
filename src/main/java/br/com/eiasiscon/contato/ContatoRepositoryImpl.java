package br.com.eiasiscon.contato;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import br.com.eiasiscon.contato.Contato;

@Repository
public class ContatoRepositoryImpl implements ContatoRepositoryCustom{
	
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public Page<Contato> find(String q, String empresa, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contato> query = builder.createQuery(Contato.class);
        Root r = query.from(Contato.class);
 
        Predicate predicate = builder.conjunction();
 
        UserSearchQueryCriteriaConsumer searchConsumer = new UserSearchQueryCriteriaConsumer(predicate, builder, r);
        
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        params.add(new SearchCriteria("firstName", ":", "John"));
        
        params.stream().forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);
 
        List<Contato> result = entityManager.createQuery(query).getResultList();
        
	    return PageableExecutionUtils.getPage(results, pageable,
	            () -> operations.count(query, Contato.class));
	}

}
