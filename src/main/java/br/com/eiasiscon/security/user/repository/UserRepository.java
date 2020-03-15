package br.com.eiasiscon.security.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eiasiscon.security.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByEmail(String email);
}
