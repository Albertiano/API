package br.com.eiasiscon.produto.tributacao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.municipio.UF;
import br.com.eiasiscon.produto.tributacao.cofins.COFINS;
import br.com.eiasiscon.produto.tributacao.cofins.COFINSST;
import br.com.eiasiscon.produto.tributacao.icms.ICMS;
import br.com.eiasiscon.produto.tributacao.ipi.IPI;
import br.com.eiasiscon.produto.tributacao.pis.PIS;
import br.com.eiasiscon.produto.tributacao.pis.PISST;

@Entity
public class Destino extends BaseEntity {
	
	@Enumerated(EnumType.STRING)
	private UF estado;
	private String cfop;
	@OneToOne(cascade = CascadeType.ALL)
	private ICMS icms;
	@OneToOne(cascade = CascadeType.ALL)
	private IPI ipi;
	@OneToOne(cascade = CascadeType.ALL)
	private PIS pis;
	@OneToOne(cascade = CascadeType.ALL)
	private PISST pisST;
	@OneToOne(cascade = CascadeType.ALL)
	private COFINS cofins;
	@OneToOne(cascade = CascadeType.ALL)
	private COFINSST cofinsST;
	
	public UF getEstado() {
		return estado;
	}
	public void setEstado(UF estado) {
		this.estado = estado;
	}
	public String getCfop() {
		return cfop;
	}
	public void setCfop(String cfop) {
		this.cfop = cfop;
	}
	public ICMS getIcms() {
		return icms;
	}
	public void setIcms(ICMS icms) {
		this.icms = icms;
	}
	public IPI getIpi() {
		return ipi;
	}
	public void setIpi(IPI ipi) {
		this.ipi = ipi;
	}
	public PIS getPis() {
		return pis;
	}
	public void setPis(PIS pis) {
		this.pis = pis;
	}
	public PISST getPisST() {
		return pisST;
	}
	public void setPisST(PISST pisST) {
		this.pisST = pisST;
	}
	public COFINS getCofins() {
		return cofins;
	}
	public void setCofins(COFINS cofins) {
		this.cofins = cofins;
	}
	public COFINSST getCofinsST() {
		return cofinsST;
	}
	public void setCofinsST(COFINSST cofinsST) {
		this.cofinsST = cofinsST;
	}

}
