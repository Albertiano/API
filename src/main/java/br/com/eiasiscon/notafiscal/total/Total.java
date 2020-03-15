package br.com.eiasiscon.notafiscal.total;

import java.math.BigDecimal;

import javax.persistence.Entity;

import br.com.eiasiscon.base.BaseEntity;

@Entity
public class Total extends BaseEntity {
	
	/*
	 * Base de Cálculo do ICMS
	*/
	private BigDecimal vBC;
	/*
	 * Valor Total do ICMS
	*/
	private BigDecimal vICMS;
	/*
	 * Valor Total do ICMS desonerado
	*/
	private BigDecimal vICMSDeson;
	/*
	 * Base de Cálculo do ICMS ST
	*/
	private BigDecimal vBCST;
	/*
	 * Valor Total do ICMS ST
	*/
	private BigDecimal vST;
	/*
	 * Valor Total dos produtos e serviços
	*/
	private BigDecimal vProd;
	/*
	 * Valor Total do Frete
	*/
	private BigDecimal vFrete;
	/*
	 * Valor Total do Seguro
	*/
	private BigDecimal vSeg;
	/*
	 * Valor Total do Desconto
	*/
	private BigDecimal vDesc;
	/*
	 * Valor Total do II
	*/
	private BigDecimal vII;
	/*
	 * Valor Total do IPI
	*/
	private BigDecimal vIPI;
	/*
	 * Valor do PIS
	*/
	private BigDecimal vPIS;
	/*
	 * Valor da COFINS
	*/
	private BigDecimal vCOFINS;
	/*
	 * Outras Despesas acessórias
	*/
	private BigDecimal vOutro;
	/*
	 * Valor Total da NF-e
	*/
	private BigDecimal vNF;
	/*
	 * Valor aproximado total de tributos federais, estaduais e municipais.
	*/
	private BigDecimal vTotTrib;
	/*
	 * Valor total do ICMS relativo Fundo de Combate à Pobreza (FCP) da UF de destino
	*/
	private BigDecimal vFCPUFDest;
	/*
	 * Valor total do ICMS Interestadual para a UF de destino
	*/
	private BigDecimal vICMSUFDest;
	/*
	 * Valor total do ICMS Interestadual para a UF do remetente
	*/
	private BigDecimal vICMSUFRemet;
	/*
	 * Valor Total do FCP (Fundo de Combate à Pobreza)
	*/
	private BigDecimal vFCP;
	/*
	 * Valor Total do FCP (Fundo de Combate à Pobreza) retido por substituição tributária
	*/
	private BigDecimal vFCPST;
	/*
	 * Valor Total do FCP retido anteriormente por Substituição Tributária
	*/
	private BigDecimal vFCPSTRet;
	/*
	 * Valor Total do IPI devolvido
	*/
	private BigDecimal vIPIDevol;
	
	public BigDecimal getvBC() {
		return vBC;
	}
	public void setvBC(BigDecimal vBC) {
		this.vBC = vBC;
	}
	public BigDecimal getvICMS() {
		return vICMS;
	}
	public void setvICMS(BigDecimal vICMS) {
		this.vICMS = vICMS;
	}
	public BigDecimal getvICMSDeson() {
		return vICMSDeson;
	}
	public void setvICMSDeson(BigDecimal vICMSDeson) {
		this.vICMSDeson = vICMSDeson;
	}
	public BigDecimal getvBCST() {
		return vBCST;
	}
	public void setvBCST(BigDecimal vBCST) {
		this.vBCST = vBCST;
	}
	public BigDecimal getvST() {
		return vST;
	}
	public void setvST(BigDecimal vST) {
		this.vST = vST;
	}
	public BigDecimal getvProd() {
		return vProd;
	}
	public void setvProd(BigDecimal vProd) {
		this.vProd = vProd;
	}
	public BigDecimal getvFrete() {
		return vFrete;
	}
	public void setvFrete(BigDecimal vFrete) {
		this.vFrete = vFrete;
	}
	public BigDecimal getvSeg() {
		return vSeg;
	}
	public void setvSeg(BigDecimal vSeg) {
		this.vSeg = vSeg;
	}
	public BigDecimal getvDesc() {
		return vDesc;
	}
	public void setvDesc(BigDecimal vDesc) {
		this.vDesc = vDesc;
	}
	public BigDecimal getvII() {
		return vII;
	}
	public void setvII(BigDecimal vII) {
		this.vII = vII;
	}
	public BigDecimal getvIPI() {
		return vIPI;
	}
	public void setvIPI(BigDecimal vIPI) {
		this.vIPI = vIPI;
	}
	public BigDecimal getvPIS() {
		return vPIS;
	}
	public void setvPIS(BigDecimal vPIS) {
		this.vPIS = vPIS;
	}
	public BigDecimal getvCOFINS() {
		return vCOFINS;
	}
	public void setvCOFINS(BigDecimal vCOFINS) {
		this.vCOFINS = vCOFINS;
	}
	public BigDecimal getvOutro() {
		return vOutro;
	}
	public void setvOutro(BigDecimal vOutro) {
		this.vOutro = vOutro;
	}
	public BigDecimal getvNF() {
		return vNF;
	}
	public void setvNF(BigDecimal vNF) {
		this.vNF = vNF;
	}
	public BigDecimal getvTotTrib() {
		return vTotTrib;
	}
	public void setvTotTrib(BigDecimal vTotTrib) {
		this.vTotTrib = vTotTrib;
	}
	public BigDecimal getvFCPUFDest() {
		return vFCPUFDest;
	}
	public void setvFCPUFDest(BigDecimal vFCPUFDest) {
		this.vFCPUFDest = vFCPUFDest;
	}
	public BigDecimal getvICMSUFDest() {
		return vICMSUFDest;
	}
	public void setvICMSUFDest(BigDecimal vICMSUFDest) {
		this.vICMSUFDest = vICMSUFDest;
	}
	public BigDecimal getvICMSUFRemet() {
		return vICMSUFRemet;
	}
	public void setvICMSUFRemet(BigDecimal vICMSUFRemet) {
		this.vICMSUFRemet = vICMSUFRemet;
	}
	public BigDecimal getvFCP() {
		return vFCP;
	}
	public void setvFCP(BigDecimal vFCP) {
		this.vFCP = vFCP;
	}
	public BigDecimal getvFCPST() {
		return vFCPST;
	}
	public void setvFCPST(BigDecimal vFCPST) {
		this.vFCPST = vFCPST;
	}
	public BigDecimal getvFCPSTRet() {
		return vFCPSTRet;
	}
	public void setvFCPSTRet(BigDecimal vFCPSTRet) {
		this.vFCPSTRet = vFCPSTRet;
	}
	public BigDecimal getvIPIDevol() {
		return vIPIDevol;
	}
	public void setvIPIDevol(BigDecimal vIPIDevol) {
		this.vIPIDevol = vIPIDevol;
	}
	
}
