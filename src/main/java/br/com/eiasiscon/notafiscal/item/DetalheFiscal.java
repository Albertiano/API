package br.com.eiasiscon.notafiscal.item;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.produto.tributacao.cofins.COFINS;
import br.com.eiasiscon.produto.tributacao.cofins.COFINSST;
import br.com.eiasiscon.produto.tributacao.icms.ICMS;
import br.com.eiasiscon.produto.tributacao.ipi.IPI;
import br.com.eiasiscon.produto.tributacao.pis.PIS;
import br.com.eiasiscon.produto.tributacao.pis.PISST;

@Entity
public class DetalheFiscal extends BaseEntity {
	
	private String cfop;
    private String extipi;
    private String genero;
    private String cEan;
    private String cEanTrib;
    private String utrib;
    private BigDecimal qTrib;
    private BigDecimal vuntrib;
    @OneToOne
    private ICMS icms;
    @OneToOne
    private IPI ipi;
    @OneToOne
    private PIS pis;
    @OneToOne
    private PISST pisST;
    @OneToOne
    private COFINS cofins;
    @OneToOne
    private COFINSST cofinsST;
    private BigDecimal vTotTrib;
    
	public String getCfop() {
		return cfop;
	}
	public void setCfop(String cfop) {
		this.cfop = cfop;
	}
	public String getExtipi() {
		return extipi;
	}
	public void setExtipi(String extipi) {
		this.extipi = extipi;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getcEan() {
		return cEan;
	}
	public void setcEan(String cEan) {
		this.cEan = cEan;
	}
	public String getcEanTrib() {
		return cEanTrib;
	}
	public void setcEanTrib(String cEanTrib) {
		this.cEanTrib = cEanTrib;
	}
	public String getUtrib() {
		return utrib;
	}
	public void setUtrib(String utrib) {
		this.utrib = utrib;
	}
	public BigDecimal getqTrib() {
		return qTrib;
	}
	public void setqTrib(BigDecimal qTrib) {
		this.qTrib = qTrib;
	}
	public BigDecimal getVuntrib() {
		return vuntrib;
	}
	public void setVuntrib(BigDecimal vuntrib) {
		this.vuntrib = vuntrib;
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
	public BigDecimal getvTotTrib() {
		return vTotTrib;
	}
	public void setvTotTrib(BigDecimal vTotTrib) {
		this.vTotTrib = vTotTrib;
	}

}
