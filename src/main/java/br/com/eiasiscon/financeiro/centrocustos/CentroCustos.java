package br.com.eiasiscon.financeiro.centrocustos;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.empresa.Empresa;

@Entity
public class CentroCustos extends BaseEntity {
	
	@ManyToOne
    private Empresa empresa;
	private String descricao;
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
