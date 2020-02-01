package br.com.eiasiscon.financeiro.lancamento;

import java.util.Date;

public class LancamentoFiltro {
	private Long idEmpresa;
	private Date ini;
	private Date fim;
	private String q;
	
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
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
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
}
