package br.com.eiasiscon.produto.tributacao.ipi;

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
public class IPI extends BaseEntity {
    /*
     * CNPJ do produtor da mercadoria, quando  diferente  do  emitente. 
     * Somente  para  os  casos  de exportação direta ou indireta. 
     */
    private String CNPJProd;
    /*
     * Código do selo de controle IPI
     */
    private String cSelo;
    /*
     * Quantidade de selo de controle
     */
    private BigDecimal qSelo;
    /*
     * Código  de  Enquadramento  Legal do IPI
     * Tabela a ser criada pela RFB, informar 999 enquanto a tabela não for criada
     */
    private String cEnq;
    /*
     * Código  da  situação  tributária  do IPI
     */
    @Enumerated(EnumType.STRING)
    private CST_IPI cstIPI;
    /*
     * Valor da BC do IPI
     */
    private BigDecimal vBCIPI;
    /*
     * Alíquota do IPI
     */
    private BigDecimal pIPI;
    /*
     * Quantidade  total  na  unidade padrão  para  tributação  (somente para  os  produtos  tributados  por unidade)
     */
    private BigDecimal qUnid;
    /*
     * Valor por Unidade Tributável 
     */
    private BigDecimal vUnid;
    /*
     * Valor do IPI 
     */
    private BigDecimal vIPI;
    /*
     * Tipo de Calculo Valor ou Percentual
     */
    @Enumerated(EnumType.STRING)
    private TpCalcIPI tpCalcIPI;
    
    private boolean vIpiBcICMS;
    private boolean vIpiBcICMSST;
}
