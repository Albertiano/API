package br.com.eiasiscon.financeiro.conta;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.empresa.Empresa;

@Entity
public class Conta extends BaseEntity {
	
	@ManyToOne
    private Empresa empresa;
	private String descricao;
	private BigDecimal saldo;
	
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
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
}
