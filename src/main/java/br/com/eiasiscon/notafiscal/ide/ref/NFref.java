package br.com.eiasiscon.notafiscal.ide.ref;

import javax.persistence.Entity;

import br.com.eiasiscon.base.BaseEntity;

@Entity
public class NFref extends BaseEntity {
	
	private String refNFe;

	public String getRefNFe() {
		return refNFe;
	}

	public void setRefNFe(String refNFe) {
		this.refNFe = refNFe;
	}
}
