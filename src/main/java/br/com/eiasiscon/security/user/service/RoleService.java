package br.com.eiasiscon.security.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eiasiscon.base.BaseService;
import br.com.eiasiscon.security.user.entity.Role;
import br.com.eiasiscon.security.user.repository.RoleRepository;

@Service
public class RoleService extends BaseService<Role, String> {
	
	@Autowired
    private RoleRepository repository;
	
	@Autowired
	public void setJpaRepository(RoleRepository jpa) {
		setJpa(jpa);
	}
    
    public RoleRepository getRoleRepository() {
        return repository;
    }
    
    public Page<Role> findAll(Pageable pageable) {
		Page<Role>  entities = repository.findAll(pageable);
		return entities;
	}
}
