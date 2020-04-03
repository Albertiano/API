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
public class PIS extends BaseEntity {
    /**
     * Código de Situação Tributária do PIS
     */
	@Enumerated(EnumType.STRING)
    private CST_PIS cstPIS;
    /**
     * Valor da Base de Cálculo do PIS
     */
    private BigDecimal vBCPIS;
    /**
     * Alíquota do PIS (em percentual)
     */
    private BigDecimal pPIS;
    /**
     * Valor do PIS
     */
    private BigDecimal vPIS;
    /**
     * Quantidade Vendida
     */
    private BigDecimal qBCProdPIS;
    /**
     * Alíquota do PIS (em reais)
     */
    private BigDecimal vAliqProdPIS;
    
    @Enumerated(EnumType.STRING)
    private TpCalcPIS tpCalcPis;
}
