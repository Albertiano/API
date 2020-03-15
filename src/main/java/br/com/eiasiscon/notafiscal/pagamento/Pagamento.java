package br.com.eiasiscon.notafiscal.pagamento;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.com.eiasiscon.base.BaseEntity;

@Entity
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