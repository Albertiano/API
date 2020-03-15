package br.com.eiasiscon.notafiscal;

import javax.persistence.Entity;

import br.com.eiasiscon.base.BaseEntity;

@Entity
public class ProcEventoNFe extends BaseEntity {

	String xmlEvento;

	public String getXmlEvento() {
		return xmlEvento;
	}

	public void setXmlEvento(String xmlEvento) {
		this.xmlEvento = xmlEvento;
	}
}
