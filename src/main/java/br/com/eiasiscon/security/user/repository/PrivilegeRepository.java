package br.com.eiasiscon.security.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eiasiscon.security.user.entity.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, String>{
}
