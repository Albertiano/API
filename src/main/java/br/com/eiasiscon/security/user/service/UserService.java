package br.com.eiasiscon.security.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eiasiscon.base.BaseService;
import br.com.eiasiscon.security.user.entity.User;
import br.com.eiasiscon.security.user.repository.UserRepository;

@Service
public class UserService extends BaseService<User, Long> {
	
	@Autowired
    private UserRepository repository;
	
	@Autowired
	public void setJpaRepository(UserRepository jpa) {
		setJpa(jpa);
	}
    
    public UserRepository getUserRepository() {
        return repository;
    }

    public Optional<User> getByUsername(String username) {
        return this.repository.findByEmail(username);
    }
    
    public Page<User> find(String query, Pageable pageable) {
		Page<User>  entities = repository.findAll(pageable);
		return entities;
	}
}
