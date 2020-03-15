package br.com.eiasiscon.produto.tributacao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.empresa.Empresa;
import br.com.eiasiscon.produto.tributacao.Destino;

@Entity
public class Tributacao  extends BaseEntity {

	@ManyToOne
    private Empresa empresa;
	String nome;
	String descricao;
	@OneToMany(cascade = CascadeType.ALL)
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
