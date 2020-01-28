package br.com.eiasiscon.security.user.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.eiasiscon.security.user.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	public Optional<User> findByEmail(String email);
	
	@Query(value = "{'$or' : ["
			+ "{'email': {$regex : '?0', $options: 'i'}},"
			+ "{'name': {$regex : '?0', $options: 'i'}},"
			+ "{'lastName': {$regex : '?0', $options: 'i'}}"
			+ "]}")
	Page<User> find(String q, Pageable page);
}
