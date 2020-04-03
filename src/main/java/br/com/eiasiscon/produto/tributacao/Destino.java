package br.com.eiasiscon.produto.tributacao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.municipio.UF;
import br.com.eiasiscon.produto.tributacao.cofins.COFINS;
import br.com.eiasiscon.produto.tributacao.cofins.COFINSST;
import br.com.eiasiscon.produto.tributacao.icms.ICMS;
import br.com.eiasiscon.produto.tributacao.ipi.IPI;
import br.com.eiasiscon.produto.tributacao.pis.PIS;
import br.com.eiasiscon.produto.tributacao.pis.PISST;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Destino extends BaseEntity {
	
	@Enumerated(EnumType.STRING)
	private UF estado;
	private String cfop;
	@OneToOne(cascade = CascadeType.ALL)
	private ICMS icms;
	@OneToOne(cascade = CascadeType.ALL)
	private IPI ipi;
	@OneToOne(cascade = CascadeType.ALL)
	private PIS pis;
	@OneToOne(cascade = CascadeType.ALL)
	private PISST pisST;
	@OneToOne(cascade = CascadeType.ALL)
	private COFINS cofins;
	@OneToOne(cascade = CascadeType.ALL)
	private COFINSST cofinsST;
}
