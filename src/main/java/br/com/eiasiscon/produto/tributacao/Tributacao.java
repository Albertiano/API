package br.com.eiasiscon.produto.tributacao;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.empresa.Empresa;
import br.com.eiasiscon.produto.tributacao.Destino;

@Document
public class Tributacao  extends BaseEntity {

	private static final long serialVersionUID = -4291567276980231944L;

	@DBRef
    private Empresa empresa;
	String nome;
	String descricao;
	List<Destino> destinos;
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Destino> getDestinos() {
		return destinos;
	}
	public void setDestinos(List<Destino> destinos) {
		this.destinos = destinos;
	}
}
