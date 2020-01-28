package br.com.eiasiscon.notafiscal.pagamento;

import java.math.BigDecimal;

public class DetPag {
	private IndPag indPag;
	private TpPag tPag;
	private BigDecimal vPag;
	private Card card;
	
	
	public BigDecimal getvPag() {
		return vPag;
	}
	public void setvPag(BigDecimal vPag) {
		this.vPag = vPag;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public TpPag gettPag() {
		return tPag;
	}
	public void settPag(TpPag tPag) {
		this.tPag = tPag;
	}
	public IndPag getIndPag() {
		return indPag;
	}
	public void setIndPag(IndPag indPag) {
		this.indPag = indPag;
	}
}
