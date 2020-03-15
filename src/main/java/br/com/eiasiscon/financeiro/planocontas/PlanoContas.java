package br.com.eiasiscon.financeiro.planocontas;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.empresa.Empresa;

@Entity
public class PlanoContas extends BaseEntity {

	@ManyToOne
    private Empresa empresa;
	private String descricao;
	private String codigo;
	@Enumerated(EnumType.STRING)
	private TpConta tpConta;
	
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public TpConta getTpConta() {
		return tpConta;
	}
	public void setTpConta(TpConta tpConta) {
		this.tpConta = tpConta;
	}
}
