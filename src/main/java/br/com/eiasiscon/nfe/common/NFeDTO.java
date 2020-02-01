package br.com.eiasiscon.nfe.common;

public class NFeDTO {
	private Long empresaID;
	private Long idNota;
	private String uf;
	private String numDoc;
	private String status;
	private String motivo;
	private String protocolo;
	private String data;
	private String recibo;
	private boolean sucesso;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public boolean isSucesso() {
		return sucesso;
	}
	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getRecibo() {
		return recibo;
	}
	public void setRecibo(String nRecibo) {
		this.recibo = nRecibo;
	}
	public Long getIdNota() {
		return idNota;
	}
	public void setIdNota(Long idNota) {
		this.idNota = idNota;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getNumDoc() {
		return numDoc;
	}
	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}
	public Long getEmpresaID() {
		return empresaID;
	}
	public void setEmpresaID(Long empresaID) {
		this.empresaID = empresaID;
	}
}
