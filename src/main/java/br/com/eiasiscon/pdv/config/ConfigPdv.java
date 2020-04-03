package br.com.eiasiscon.pdv.config;

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
public class ConfigPdv extends BaseEntity {
	
	@ManyToOne
    private Empresa empresa;
	
	private String descricao;
	private Boolean isExterno;
	private int serieNFe;
	private int numeroNFe;
	private int serieNFCe;
	private int numeroNFCe;
}
