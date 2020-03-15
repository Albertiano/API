package br.com.eiasiscon.notafiscal.pagamento;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import br.com.eiasiscon.base.BaseEntity;

@Entity
public class DetPag extends BaseEntity {
	@Enumerated(EnumType.STRING)
	private IndPag indPag;
	@Enumerated(EnumType.STRING)
	private TpPag tPag;
	private BigDecimal vPag;
	@OneToOne(cascade = CascadeType.ALL)
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
