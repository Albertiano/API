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
public class COFINS extends BaseEntity {
    /**
     * Código de Situação Tributária do COFINS
     */
	@Enumerated(EnumType.STRING)
    private CST_COFINS cstCOFINS;
    /**
     * Valor da Base de Cálculo do COFINS
     */
    private BigDecimal vBCCOFINS;
    /**
     * Alíquota do COFINS (em percentual)
     */
    private BigDecimal pCOFINS;
    /**
     * Valor do COFINS
     */
    private BigDecimal vCOFINS;
    /**
     * Quantidade Vendida
     */
    private BigDecimal qBCProdCOFINS;
    /**
     * Alíquota do COFINS (em reais)
     */
    private BigDecimal vAliqProdCOFINS;
    
    @Enumerated(EnumType.STRING)
    private TpCalcCOFINS tpCalcCofins;
}
