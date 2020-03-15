package br.com.eiasiscon.security.user.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import br.com.eiasiscon.base.BaseEntity;

@Entity
public class User extends BaseEntity {
	
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	@NotEmpty(message = "*Please provide your password")
	private String password;
	@NotEmpty(message = "*Please provide your name")
	private String name;
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;
	@OneToMany
	private Collection<Role> roles;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}
