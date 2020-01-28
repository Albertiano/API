/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eiasiscon.produto.tributacao.icms;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.eiasiscon.municipio.UF;

public class ICMS implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * Origem da mercadoria
     *
     */
    private Origem origem;
    /**
     * Tributação do ICMS
     *
     */
    private CST_ICMS cstICMS;
    /**
     * Modalidade de determinação da BC do ICMS
     *
     */
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
    private MotDesICMS motDesICMS;
    /**
     * Modalidade de determinação da BC do ICMS ST
     *
     */
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

    public ICMS() {
    }

    /**
     * @return the origem
     */
    public Origem getOrigem() {
        return origem;
    }

    /**
     * @param origem the origem to set
     */
    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    /**
     * @return the cstICMS
     */
    public CST_ICMS getCstICMS() {
        return cstICMS;
    }

    /**
     * @param cstICMS the cstICMS to set
     */
    public void setCstICMS(CST_ICMS cstICMS) {
        this.cstICMS = cstICMS;
    }

    /**
     * @return the modBCICMS
     */
    public ModBC getModBCICMS() {
        return modBCICMS;
    }

    /**
     * @param modBCICMS the modBCICMS to set
     */
    public void setModBCICMS(ModBC modBCICMS) {
        this.modBCICMS = modBCICMS;
    }

    /**
     * @return the vBCICMS
     */
    public BigDecimal getvBCICMS() {
        return vBCICMS;
    }

    /**
     * @param vBCICMS the vBCICMS to set
     */
    public void setvBCICMS(BigDecimal vBCICMS) {
        this.vBCICMS = vBCICMS;
    }

    /**
     * @return the pRedBCICMS
     */
    public BigDecimal getpRedBCICMS() {
        return pRedBCICMS;
    }

    /**
     * @param pRedBCICMS the pRedBCICMS to set
     */
    public void setpRedBCICMS(BigDecimal pRedBCICMS) {
        this.pRedBCICMS = pRedBCICMS;
    }

    /**
     * @return the pICMS
     */
    public BigDecimal getpICMS() {
        return pICMS;
    }

    /**
     * @param pICMS the pICMS to set
     */
    public void setpICMS(BigDecimal pICMS) {
        this.pICMS = pICMS;
    }

    /**
     * @return the vICMS
     */
    public BigDecimal getvICMS() {
        return vICMS;
    }

    /**
     * @param vICMS the vICMS to set
     */
    public void setvICMS(BigDecimal vICMS) {
        this.vICMS = vICMS;
    }

    /**
     * @return the motDesICMS
     */
    public MotDesICMS getMotDesICMS() {
        return motDesICMS;
    }

    /**
     * @param motDesICMS the motDesICMS to set
     */
    public void setMotDesICMS(MotDesICMS motDesICMS) {
        this.motDesICMS = motDesICMS;
    }

    /**
     * @return the modBCST
     */
    public ModBCST getModBCST() {
        return modBCST;
    }

    /**
     * @param modBCST the modBCST to set
     */
    public void setModBCST(ModBCST modBCST) {
        this.modBCST = modBCST;
    }

    /**
     * @return the pMVAST
     */
    public BigDecimal getpMVAST() {
        return pMVAST;
    }

    /**
     * @param pMVAST the pMVAST to set
     */
    public void setpMVAST(BigDecimal pMVAST) {
        this.pMVAST = pMVAST;
    }

    /**
     * @return the pRedBCST
     */
    public BigDecimal getpRedBCST() {
        return pRedBCST;
    }

    /**
     * @param pRedBCST the pRedBCST to set
     */
    public void setpRedBCST(BigDecimal pRedBCST) {
        this.pRedBCST = pRedBCST;
    }

    /**
     * @return the vBCST
     */
    public BigDecimal getvBCST() {
        return vBCST;
    }

    /**
     * @param vBCST the vBCST to set
     */
    public void setvBCST(BigDecimal vBCST) {
        this.vBCST = vBCST;
    }

    /**
     * @return the pICMSST
     */
    public BigDecimal getpICMSST() {
        return pICMSST;
    }

    /**
     * @param pICMSST the pICMSST to set
     */
    public void setpICMSST(BigDecimal pICMSST) {
        this.pICMSST = pICMSST;
    }

    /**
     * @return the vICMSST
     */
    public BigDecimal getvICMSST() {
        return vICMSST;
    }

    /**
     * @param vICMSST the vICMSST to set
     */
    public void setvICMSST(BigDecimal vICMSST) {
        this.vICMSST = vICMSST;
    }

    /**
     * @return the vBCSTRet
     */
    public BigDecimal getvBCSTRet() {
        return vBCSTRet;
    }

    /**
     * @param vBCSTRet the vBCSTRet to set
     */
    public void setvBCSTRet(BigDecimal vBCSTRet) {
        this.vBCSTRet = vBCSTRet;
    }

    /**
     * @return the vICMSSTRet
     */
    public BigDecimal getvICMSSTRet() {
        return vICMSSTRet;
    }

    /**
     * @param vICMSSTRet the vICMSSTRet to set
     */
    public void setvICMSSTRet(BigDecimal vICMSSTRet) {
        this.vICMSSTRet = vICMSSTRet;
    }

    /**
     * @return the vBCSTDest
     */
    public BigDecimal getvBCSTDest() {
        return vBCSTDest;
    }

    /**
     * @param vBCSTDest the vBCSTDest to set
     */
    public void setvBCSTDest(BigDecimal vBCSTDest) {
        this.vBCSTDest = vBCSTDest;
    }

    /**
     * @return the vICMSSTDest
     */
    public BigDecimal getvICMSSTDest() {
        return vICMSSTDest;
    }

    /**
     * @param vICMSSTDest the vICMSSTDest to set
     */
    public void setvICMSSTDest(BigDecimal vICMSSTDest) {
        this.vICMSSTDest = vICMSSTDest;
    }

    /**
     * @return the pBCOp
     */
    public BigDecimal getpBCOp() {
        return pBCOp;
    }

    /**
     * @param pBCOp the pBCOp to set
     */
    public void setpBCOp(BigDecimal pBCOp) {
        this.pBCOp = pBCOp;
    }

    /**
     * @return the UFST
     */
    public UF getUFST() {
        return UFST;
    }

    /**
     * @param UFST the UFST to set
     */
    public void setUFST(UF UFST) {
        this.UFST = UFST;
    }

    /**
     * @return the pCredSN
     */
    public BigDecimal getpCredSN() {
        return pCredSN;
    }

    /**
     * @param pCredSN the pCredSN to set
     */
    public void setpCredSN(BigDecimal pCredSN) {
        this.pCredSN = pCredSN;
    }

    /**
     * @return the vCredICMSSN
     */
    public BigDecimal getvCredICMSSN() {
        return vCredICMSSN;
    }

    /**
     * @param vCredICMSSN the vCredICMSSN to set
     */
    public void setvCredICMSSN(BigDecimal vCredICMSSN) {
        this.vCredICMSSN = vCredICMSSN;
    }

	public BigDecimal getvICMSDeson() {
		return vICMSDeson;
	}

	public void setvICMSDeson(BigDecimal vICMSDeson) {
		this.vICMSDeson = vICMSDeson;
	}

	public BigDecimal getvICMSOp() {
		return vICMSOp;
	}

	public void setvICMSOp(BigDecimal vICMSOp) {
		this.vICMSOp = vICMSOp;
	}

	public BigDecimal getpDif() {
		return pDif;
	}

	public void setpDif(BigDecimal pDif) {
		this.pDif = pDif;
	}

	public BigDecimal getvICMSDif() {
		return vICMSDif;
	}

	public void setvICMSDif(BigDecimal vICMSDif) {
		this.vICMSDif = vICMSDif;
	}

	public BigDecimal getvBCFCP() {
		return vBCFCP;
	}

	public void setvBCFCP(BigDecimal vBCFCP) {
		this.vBCFCP = vBCFCP;
	}

	public BigDecimal getpFCP() {
		return pFCP;
	}

	public void setpFCP(BigDecimal pFCP) {
		this.pFCP = pFCP;
	}

	public BigDecimal getvFCP() {
		return vFCP;
	}

	public void setvFCP(BigDecimal vFCP) {
		this.vFCP = vFCP;
	}

	public BigDecimal getvBCFCPST() {
		return vBCFCPST;
	}

	public void setvBCFCPST(BigDecimal vBCFCPST) {
		this.vBCFCPST = vBCFCPST;
	}

	public BigDecimal getpFCPST() {
		return pFCPST;
	}

	public void setpFCPST(BigDecimal pFCPST) {
		this.pFCPST = pFCPST;
	}

	public BigDecimal getvFCPST() {
		return vFCPST;
	}

	public void setvFCPST(BigDecimal vFCPST) {
		this.vFCPST = vFCPST;
	}

	public BigDecimal getpST() {
		return pST;
	}

	public void setpST(BigDecimal pST) {
		this.pST = pST;
	}

	public BigDecimal getvBCFCPSTRet() {
		return vBCFCPSTRet;
	}

	public void setvBCFCPSTRet(BigDecimal vBCFCPSTRet) {
		this.vBCFCPSTRet = vBCFCPSTRet;
	}

	public BigDecimal getpFCPSTRet() {
		return pFCPSTRet;
	}

	public void setpFCPSTRet(BigDecimal pFCPSTRet) {
		this.pFCPSTRet = pFCPSTRet;
	}

	public BigDecimal getvFCPSTRet() {
		return vFCPSTRet;
	}

	public void setvFCPSTRet(BigDecimal vFCPSTRet) {
		this.vFCPSTRet = vFCPSTRet;
	}
}
