package br.com.eiasiscon.notafiscal;

import javax.persistence.Entity;
import javax.persistence.Lob;

import br.com.eiasiscon.base.BaseEntity;

@Entity
public class ProcEventoNFe extends BaseEntity {

	@Lob
	String xmlEvento;

	public String getXmlEvento() {
		return xmlEvento;
	}

	public void setXmlEvento(String xmlEvento) {
		this.xmlEvento = xmlEvento;
	}
}
