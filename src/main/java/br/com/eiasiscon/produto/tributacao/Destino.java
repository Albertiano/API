package br.com.eiasiscon.produto.tributacao;

import br.com.eiasiscon.municipio.UF;
import br.com.eiasiscon.produto.tributacao.cofins.COFINS;
import br.com.eiasiscon.produto.tributacao.cofins.COFINSST;
import br.com.eiasiscon.produto.tributacao.icms.ICMS;
import br.com.eiasiscon.produto.tributacao.ipi.IPI;
import br.com.eiasiscon.produto.tributacao.pis.PIS;
import br.com.eiasiscon.produto.tributacao.pis.PISST;

public class Destino {
	
	private UF estado;
	private String cfop;
	private ICMS icms;
	private IPI ipi;
	private PIS pis;
	private PISST pisST;
	private COFINS cofins;
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
