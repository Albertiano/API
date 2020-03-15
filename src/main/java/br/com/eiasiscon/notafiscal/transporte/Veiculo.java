package br.com.eiasiscon.notafiscal.transporte;

import javax.persistence.Entity;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.municipio.UF;

@Entity
public class Veiculo extends BaseEntity {

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
