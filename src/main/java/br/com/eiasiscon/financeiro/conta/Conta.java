package br.com.eiasiscon.financeiro.conta;

import java.math.BigDecimal;

import javax.persistence.Entity;
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
public class Conta extends BaseEntity {
	
	@ManyToOne
    private Empresa empresa;
	private String descricao;
	private BigDecimal saldo;
}
