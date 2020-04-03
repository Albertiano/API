package br.com.eiasiscon.financeiro.lancamento;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.contato.Contato;
import br.com.eiasiscon.empresa.Empresa;
import br.com.eiasiscon.financeiro.centrocustos.CentroCusto;
import br.com.eiasiscon.financeiro.conta.Conta;
import br.com.eiasiscon.financeiro.planocontas.PlanoContas;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
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
	@Enumerated(EnumType.STRING)
	private TpLancamento tpLancamento;
	@Enumerated(EnumType.STRING)
	private TpPagamento tpPagamento;
	@ManyToOne
	private Conta conta;	
	@ManyToOne
	private PlanoContas planoContas;
	@ManyToOne
	private CentroCusto centroCustos;
	private String obs;
}
