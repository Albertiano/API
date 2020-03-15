package br.com.eiasiscon.produto.unidade;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.empresa.Empresa;

@Entity
public class Unidade extends BaseEntity {
	
	@ManyToOne
    private Empresa empresa;
	
	private String sigla, desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
