package br.com.eiasiscon.notafiscal.transporte;

import br.com.eiasiscon.municipio.UF;

public class Veiculo {

	private String placa;
	private UF uf;
	private String rntc;
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public UF getUf() {
		return uf;
	}
	public void setUf(UF uf) {
		this.uf = uf;
	}
	public String getRntc() {
		return rntc;
	}
	public void setRntc(String rntc) {
		this.rntc = rntc;
	}
}
