package br.com.eiasiscon.notafiscal.cobranca;

import java.math.BigDecimal;
import javax.persistence.Entity;
import br.com.eiasiscon.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Fatura extends BaseEntity {

	private String nFat;
	private BigDecimal vOrig;
	private BigDecimal vDesc;
	private BigDecimal vLiq;
}
