package br.com.eiasiscon.produto.unidade;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.empresa.Empresa;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Unidade extends BaseEntity {
	
	@ManyToOne
    private Empresa empresa;
	
	private String sigla, desc;
}
