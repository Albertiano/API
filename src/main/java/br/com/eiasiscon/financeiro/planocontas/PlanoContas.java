package br.com.eiasiscon.financeiro.planocontas;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.empresa.Empresa;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class PlanoContas extends BaseEntity {

	@ManyToOne
    private Empresa empresa;
	private String descricao;
	private String codigo;
	@Enumerated(EnumType.STRING)
	private TpConta tpConta;
}
