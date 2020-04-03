package br.com.eiasiscon.notafiscal.transporte;

import javax.persistence.Entity;
import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.municipio.UF;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Veiculo extends BaseEntity {

	private String placa;
	private UF uf;
	private String rntc;
}
