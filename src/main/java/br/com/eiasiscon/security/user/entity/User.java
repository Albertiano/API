package br.com.eiasiscon.security.user.entity;

import java.util.Collection;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.eiasiscon.base.BaseEntity;

@Document
public class User extends BaseEntity {
	
	private static final long serialVersionUID = -6703244807591424234L;
	
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	@Length(min = 4, message = "*Your password must have at least 4 characters")
	@NotEmpty(message = "*Please provide your password")
	private String password;
	@NotEmpty(message = "*Please provide your name")
	private String name;
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;
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
