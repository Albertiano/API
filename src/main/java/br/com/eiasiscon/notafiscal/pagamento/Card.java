package br.com.eiasiscon.notafiscal.pagamento;

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
public class Card extends BaseEntity {
	
	private String cnpj;
	@Enumerated(EnumType.STRING)
	private TpBand tBand;
	private String cAut;
}
