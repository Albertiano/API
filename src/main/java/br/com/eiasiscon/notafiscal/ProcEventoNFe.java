package br.com.eiasiscon.notafiscal;

import javax.persistence.Entity;
import javax.persistence.Lob;

import br.com.eiasiscon.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class ProcEventoNFe extends BaseEntity {

	@Lob
	String xmlEvento;
}
