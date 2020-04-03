package br.com.eiasiscon.pais;

import javax.persistence.Entity;
import br.com.eiasiscon.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Pais extends BaseEntity {
    
	private int cPais;
    private String xPais;
}