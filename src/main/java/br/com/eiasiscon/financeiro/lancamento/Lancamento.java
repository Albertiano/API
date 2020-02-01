package br.com.eiasiscon.financeiro.lancamento;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.contato.Contato;
import br.com.eiasiscon.empresa.Empresa;
import br.com.eiasiscon.financeiro.centrocustos.CentroCustos;
import br.com.eiasiscon.financeiro.conta.Conta;
import br.com.eiasiscon.financeiro.planocontas.PlanoContas;

@Entity
public class Lancamento extends BaseEntity {

	@ManyToOne
    private Empresa empresa;
	@NotNull
	private Date competencia;
	@ManyToOne
	private Contato contato;
	private String descricao;
	private String documento;
	private BigDecimal valor;
	private TpLancamento tpLancamento;
	private TpPagamento tpPagamento;
	@ManyToOne
	private Conta conta;	
	@ManyToOne
	private PlanoContas planoContas;
	@ManyToOne
	private CentroCustos centroCustos;
	private String obs;
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Date getCompetencia() {
		return competencia;
	}
	public void setCompetencia(Date competencia) {
		this.competencia = competencia;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TpLancamento getTpLancamento() {
		return tpLancamento;
	}
	public void setTpLancamento(TpLancamento tpLancamento) {
		this.tpLancamento = tpLancamento;
	}
	public TpPagamento getTpPagamento() {
		return tpPagamento;
	}
	public void setTpPagamento(TpPagamento tpPagamento) {
		this.tpPagamento = tpPagamento;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public PlanoContas getPlanoContas() {
		return planoContas;
	}
	public void setPlanoContas(PlanoContas planoContas) {
		this.planoContas = planoContas;
	}
	public CentroCustos getCentroCustos() {
		return centroCustos;
	}
	public void setCentroCustos(CentroCustos centroCustos) {
		this.centroCustos = centroCustos;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
}
