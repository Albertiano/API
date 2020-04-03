package br.com.eiasiscon.notafiscal.transporte;

import javax.persistence.Entity;
import br.com.eiasiscon.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Lacre extends BaseEntity {
	
	private String nLacre;
}
