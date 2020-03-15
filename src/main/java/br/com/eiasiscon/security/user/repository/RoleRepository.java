package br.com.eiasiscon.security.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eiasiscon.security.user.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

}