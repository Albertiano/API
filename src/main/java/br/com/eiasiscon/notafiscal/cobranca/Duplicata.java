package br.com.eiasiscon.notafiscal.cobranca;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import br.com.eiasiscon.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Duplicata extends BaseEntity {
	
	private String numero;
	private Date vencimento;
    private BigDecimal valor;
}
