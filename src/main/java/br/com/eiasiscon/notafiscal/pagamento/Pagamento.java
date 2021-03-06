package br.com.eiasiscon.notafiscal.pagamento;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import br.com.eiasiscon.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Pagamento extends BaseEntity {
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<DetPag> detPag;

	public List<DetPag> getDetPag() {
		return detPag;
	}

	public void setDetPag(List<DetPag> detPag) {
		this.detPag = detPag;
	}
}