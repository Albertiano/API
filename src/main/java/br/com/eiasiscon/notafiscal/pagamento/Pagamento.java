package br.com.eiasiscon.notafiscal.pagamento;

import java.util.List;

public class Pagamento {
	
	private List<DetPag> detPag;

	public List<DetPag> getDetPag() {
		return detPag;
	}

	public void setDetPag(List<DetPag> detPag) {
		this.detPag = detPag;
	}
}