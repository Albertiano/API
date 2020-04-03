package br.com.eiasiscon.security.user.entity;

import javax.persistence.Entity;
import br.com.eiasiscon.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Privilege extends BaseEntity {
	
	private String name;
	
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
