package br.com.eiasiscon.security.user.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import br.com.eiasiscon.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Role extends BaseEntity {
	
	private String name;
	@OneToMany(cascade = CascadeType.ALL)
	private Collection<Privilege> privileges;
}
