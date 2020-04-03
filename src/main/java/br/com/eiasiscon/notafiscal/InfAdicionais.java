package br.com.eiasiscon.notafiscal;

import javax.persistence.Entity;

import br.com.eiasiscon.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class InfAdicionais extends BaseEntity {
	
	private String infAdFisco;
    private String infCpl;
    // private List<ProcRef> procRef;
}
