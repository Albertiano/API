package br.com.eiasiscon.notafiscal.cobranca;

import java.util.List;

public class Cobranca {
	
	private Fatura fat;
	private List<Duplicata> dup;
	
	public Fatura getFat() {
		return fat;
	}
	public void setFat(Fatura fat) {
		this.fat = fat;
	}
	public List<Duplicata> getDup() {
		return dup;
	}
	public void setDup(List<Duplicata> dup) {
		this.dup = dup;
	}
}
