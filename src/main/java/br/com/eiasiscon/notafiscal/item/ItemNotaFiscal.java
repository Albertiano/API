package br.com.eiasiscon.notafiscal.item;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.produto.Produto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class ItemNotaFiscal extends BaseEntity {
	
	private int nItem;
	@OneToOne
	private Produto produto;
	@OneToOne
	private DetalheFiscal detFiscal;
	private BigDecimal quantidade;
	private BigDecimal precoVenda;
	private BigDecimal pesoBruto;
	private BigDecimal pesoLiquido;
	private BigDecimal subtotal;
	private BigDecimal vFrete;
	private BigDecimal vDesc;
	private BigDecimal vSeg;
	private BigDecimal vOutro;
	private boolean noValorNota;
	private String ifAdic;
}
