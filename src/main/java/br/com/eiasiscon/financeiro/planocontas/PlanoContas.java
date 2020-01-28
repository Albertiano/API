package br.com.eiasiscon.financeiro.planocontas;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.empresa.Empresa;

@Document
public class PlanoContas extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@DBRef
    private Empresa empresa;
	private String descricao;
	private String codigo;
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
