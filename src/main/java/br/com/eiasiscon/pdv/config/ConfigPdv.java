package br.com.eiasiscon.pdv.config;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.empresa.Empresa;

@Document
public class ConfigPdv extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -4591790684476266712L;
	
	@DBRef
    private Empresa empresa;
	
	private String descricao;
	private Boolean isExterno;
	private int serieNFe;
	private int numeroNFe;
	private int serieNFCe;
	private int numeroNFCe;
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean getIsExterno() {
		return isExterno;
	}
	public void setIsExterno(Boolean isExterno) {
		this.isExterno = isExterno;
	}
	public int getSerieNFe() {
		return serieNFe;
	}
	public void setSerieNFe(int serieNFe) {
		this.serieNFe = serieNFe;
	}
	public int getNumeroNFe() {
		return numeroNFe;
	}
	public void setNumeroNFe(int numeroNFe) {
		this.numeroNFe = numeroNFe;
	}
	public int getSerieNFCe() {
		return serieNFCe;
	}
	public void setSerieNFCe(int serieNFCe) {
		this.serieNFCe = serieNFCe;
	}
	public int getNumeroNFCe() {
		return numeroNFCe;
	}
	public void setNumeroNFCe(int numeroNFCe) {
		this.numeroNFCe = numeroNFCe;
	}
	
}
