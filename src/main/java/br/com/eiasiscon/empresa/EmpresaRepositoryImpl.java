package br.com.eiasiscon.empresa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import br.com.eiasiscon.security.user.entity.User;


@Repository
public class EmpresaRepositoryImpl implements EmpresaRepositoryCustom{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	public EmpresaRepositoryImpl(EntityManager entityManager) {
		Assert.notNull(entityManager, "[Assertion failed] - this argument is required; it must not be null");
		this.entityManager = entityManager;
	}

	@Override
	public Page<Empresa> find(String q, Long idUser, Pageable pageable) {
		 
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Empresa> query = cb.createQuery(Empresa.class);
        Root<Empresa> root = query.from(Empresa.class);
  
		List<Predicate> predicates = new ArrayList<>();
				
		if ((q != null) && (!(q.isEmpty())) && (!q.equalsIgnoreCase("all"))) {
			predicates.add(cb.like(cb.upper(root.<String> get("nome")), "%" + q.toUpperCase() + "%"));
			predicates.add(cb.like(cb.upper(root.<String> get("contato")), "%" + q.toUpperCase() + "%"));
			predicates.add(cb.like(cb.upper(root.<String> get("fantasia")), "%" + q.toUpperCase() + "%"));
			predicates.add(cb.like(cb.upper(root.<String> get("numDoc")), "%" + q.toUpperCase() + "%"));
		}

		Subquery<User> subquery = query.subquery(User.class);
		Root<User> user = subquery.from(User.class);
		subquery.select(user)
			.distinct(true)
			.where(cb.equal(user.get("id"), idUser));

		predicates.add(cb.in(root.get("users")).value(subquery));
		
		query.select(root)
			.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

		query.orderBy(cb.asc(root.get("nome")));
 
        List<Empresa> result = entityManager.createQuery(query).getResultList();
        
	    return PageableExecutionUtils.getPage(result, pageable,
	            () -> result.size());
	}

}
