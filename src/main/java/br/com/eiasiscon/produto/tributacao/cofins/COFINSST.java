package br.com.eiasiscon.produto.tributacao.cofins;

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
public class COFINSST extends BaseEntity {
    /**
     * Código de Situação Tributária do COFINS
     */
	@Enumerated(EnumType.STRING)
    private TpCalcCOFINS tpCalcCOFINSST;
    /**
     * Valor da Base de Cálculo do COFINS
     */
    private BigDecimal vBCCOFINSST;
    /**
     * Alíquota do COFINS (em percentual)
     */
    private BigDecimal pCOFINSST;
    /**
     * Valor do COFINS
     */
    private BigDecimal vCOFINSST;
    /**
     * Quantidade Vendida
     */
    private BigDecimal qBCProdCOFINSST;
    /**
     * Alíquota do COFINS (em reais)
     */
    private BigDecimal vAliqProdCOFINSST;   
}
