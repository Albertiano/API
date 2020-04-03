package br.com.eiasiscon.produto.tributacao.pis;

import java.math.BigDecimal;
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
public class PISST extends BaseEntity {
    /**
     * Código de Situação Tributária do PIS
     */
	@Enumerated(EnumType.STRING)
    private TpCalcPIS tpCalcPISST;
    /**
     * Valor da Base de Cálculo do PIS
     */
    private BigDecimal vBCPISST;
    /**
     * Alíquota do PIS (em percentual)
     */
    private BigDecimal pPISST;
    /**
     * Valor do PIS
     */
    private BigDecimal vPISST;
    /**
     * Quantidade Vendida
     */
    private BigDecimal qBCProdPISST;
    /**
     * Alíquota do PIS (em reais)
     */
    private BigDecimal vAliqProdPISST;
}
