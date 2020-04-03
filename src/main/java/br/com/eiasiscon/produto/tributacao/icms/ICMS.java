package br.com.eiasiscon.produto.tributacao.icms;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.municipio.UF;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class ICMS extends BaseEntity {
    /**
     * Origem da mercadoria
     *
     */
    @Enumerated(EnumType.STRING)
    private Origem origem;
    /**
     * Tributação do ICMS
     *
     */
    @Enumerated(EnumType.STRING)
    private CST_ICMS cstICMS;
    /**
     * Modalidade de determinação da BC do ICMS
     *
     */
    @Enumerated(EnumType.STRING)
    private ModBC modBCICMS;
    /**
     * Valor da BC do ICMS
     *
     */
    private BigDecimal vBCICMS;
    /**
     * Percentual da Redução de BC
     *
     */
    private BigDecimal pRedBCICMS;
    /**
     * Alíquota do imposto
     *
     */
    private BigDecimal pICMS;
    /**
     * Valor do ICMS 
     *
     */
    private BigDecimal vICMS;
    /**
     * Motivo da desoneração do ICMS
     *
     */
    @Enumerated(EnumType.STRING)
    private MotDesICMS motDesICMS;
    /**
     * Modalidade de determinação da BC do ICMS ST
     *
     */
    @Enumerated(EnumType.STRING)
    private ModBCST modBCST;
    /**
     * Percentual da margem de valor Adicionado do ICMS ST
     *
     */
    private BigDecimal pMVAST;
    /**
     * Percentual da Redução de BC do ICMS ST
     *
     */
    private BigDecimal pRedBCST;
    /**
     * Valor da BC do ICMS ST
     *
     */
    private BigDecimal vBCST;
    /**
     * Alíquota do imposto do ICMS ST 
     *
     */
    private BigDecimal pICMSST;
    /**
     * Valor do ICMS ST
     *
     */
    private BigDecimal vICMSST;
    /**
     * Valor da BC do ICMS ST retido
     *
     */
    private BigDecimal vBCSTRet;
    /**
     * Valor do ICMS ST retido
     *
     */
    private BigDecimal vICMSSTRet;
    /**
     * Valor da BC do ICMS ST da UF destino
     *
     */
    private BigDecimal vBCSTDest;
    /**
     * Valor do ICMS ST da UF destino
     *
     */
    private BigDecimal vICMSSTDest;
    /**
     * Percentual da BC operação própria 
     *
     */
    private BigDecimal pBCOp;
    /**
     * UF para qual é devido o ICMS ST
     *
     */
    @Enumerated(EnumType.STRING)
    private UF UFST;
    /**
     * Alíquota aplicável de cálculo do crédito (Simples Nacional). 
     *
     */
    private BigDecimal pCredSN;
    /**
     * Valor crédito do ICMS que pode ser aproveitado nos termos do art. 23 da
     * LC 123 (Simples Nacional)     *
     */
    private BigDecimal vCredICMSSN;    
    /*
     * Valor do ICMS desonerado
     */
    
    private BigDecimal vICMSDeson;
    /*
     * Valor do ICMS da Operação
     * Valor como se não tivesse o diferimento
     */
    private BigDecimal vICMSOp;
    /*
     * Percentual do diferimento
     */
    private BigDecimal pDif;
    /*
     * Valor do ICMS diferido
     */
    private BigDecimal vICMSDif;
    /*
     * Valor da Base de Cálculo do FCP
     */
    private BigDecimal vBCFCP;
    /*
     * Percentual do Fundo de Combate à Pobreza (FCP)
     */
    private BigDecimal pFCP;
    /*
     * Valor do Fundo de Combate à Pobreza (FCP)
     */
    private BigDecimal vFCP;
    /*
     * Valor da Base de Cálculo do FCP retido por Substituição Tributária
     */
    private BigDecimal vBCFCPST;
    /*
     * Percentual do FCP retido por Substituição Tributária
     */
    private BigDecimal pFCPST;
    /*
     * Valor do FCP retido por Substituição Tributária
     */
    private BigDecimal vFCPST;
    /*
     * Alíquota suportada pelo Consumidor Final
     */
    private BigDecimal pST;
    /*
     * Valor da Base de Cálculo do FCP retido anteriormente
     */
    private BigDecimal vBCFCPSTRet;
    /*
     * Percentual do FCP retido anteriormente por Substituição Tributária
     */
    private BigDecimal pFCPSTRet;
    /*
     * Valor do FCP retido anteriormente por Substituição Tributária
     */
    private BigDecimal vFCPSTRet;
}
