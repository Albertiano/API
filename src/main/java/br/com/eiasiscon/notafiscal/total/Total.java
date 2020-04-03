package br.com.eiasiscon.notafiscal.total;

import java.math.BigDecimal;
import javax.persistence.Entity;
import br.com.eiasiscon.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
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
}
