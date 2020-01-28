package br.com.eiasiscon.notafiscal.pagamento;

public class Card {
	
	private String cnpj;
	private TpBand tBand;
	private String cAut;
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public TpBand gettBand() {
		return tBand;
	}
	public void settBand(TpBand tBand) {
		this.tBand = tBand;
	}
	public String getcAut() {
		return cAut;
	}
	public void setcAut(String cAut) {
		this.cAut = cAut;
	}
}
