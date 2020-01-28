package br.com.eiasiscon.ibpt;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.eiasiscon.base.BaseEntity;

@Document(collection="IBPT")
public class IBPT  extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	String codigo;
	String tipo;
	String descricao;
	double nacionalFederal;
	double importadosFederal;
	double estadual;
	double municipal;
	LocalDate  vigenciaInicio;
	LocalDate  vigenciaFim;
	String chave;
	String versao;
	String fonte;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getNacionalFederal() {
		return nacionalFederal;
	}
	public void setNacionalFederal(double nacionalFederal) {
		this.nacionalFederal = nacionalFederal;
	}
	public double getImportadosFederal() {
		return importadosFederal;
	}
	public void setImportadosFederal(double importadosFederal) {
		this.importadosFederal = importadosFederal;
	}
	public double getEstadual() {
		return estadual;
	}
	public void setEstadual(double estadual) {
		this.estadual = estadual;
	}
	public double getMunicipal() {
		return municipal;
	}
	public void setMunicipal(double municipal) {
		this.municipal = municipal;
	}
	public LocalDate getVigenciaInicio() {
		return vigenciaInicio;
	}
	public void setVigenciaInicio(LocalDate vigenciaInicio) {
		this.vigenciaInicio = vigenciaInicio;
	}
	public LocalDate getVigenciaFim() {
		return vigenciaFim;
	}
	public void setVigenciaFim(LocalDate vigenciaFim) {
		this.vigenciaFim = vigenciaFim;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getVersao() {
		return versao;
	}
	public void setVersao(String versao) {
		this.versao = versao;
	}
	public String getFonte() {
		return fonte;
	}
	public void setFonte(String fonte) {
		this.fonte = fonte;
	}
	
}
