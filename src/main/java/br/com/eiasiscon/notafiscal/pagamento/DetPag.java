package br.com.eiasiscon.notafiscal.pagamento;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import br.com.eiasiscon.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class DetPag extends BaseEntity {
	@Enumerated(EnumType.STRING)
	private IndPag indPag;
	@Enumerated(EnumType.STRING)
	private TpPag tPag;
	private BigDecimal vPag;
	@OneToOne(cascade = CascadeType.ALL)
	private Card card;
}
