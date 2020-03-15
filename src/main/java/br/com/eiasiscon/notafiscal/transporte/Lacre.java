package br.com.eiasiscon.notafiscal.transporte;

import javax.persistence.Entity;

import br.com.eiasiscon.base.BaseEntity;

@Entity
public class Lacre extends BaseEntity {
	
	private String nLacre;

	public String getnLacre() {
		return nLacre;
	}

	public void setnLacre(String nLacre) {
		this.nLacre = nLacre;
	}
}
