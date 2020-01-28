package br.com.eiasiscon.notafiscal;

import java.util.Date;

public class NotaFiscalDTO {
	
	private Date ini;
	private Date fim;
	private String empresa;
	
	public Date getIni() {
		return ini;
	}
	public void setIni(Date ini) {
		this.ini = ini;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

}
