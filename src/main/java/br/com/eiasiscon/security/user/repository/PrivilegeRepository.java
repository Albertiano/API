package br.com.eiasiscon.security.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.eiasiscon.security.user.entity.Privilege;

@Repository
public interface PrivilegeRepository extends MongoRepository<Privilege, String>{
}
