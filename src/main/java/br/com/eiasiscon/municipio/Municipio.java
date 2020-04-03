package br.com.eiasiscon.municipio;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.eiasiscon.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Municipio extends BaseEntity {
    
	private int cMun;
    private String xMun;
    @Enumerated(EnumType.STRING)
    private UF uf;
}
