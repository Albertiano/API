package br.com.eiasiscon.empresa;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.contato.TpDoc;
import br.com.eiasiscon.empresa.CRT;
import br.com.eiasiscon.municipio.Municipio;
import br.com.eiasiscon.pais.Pais;
import br.com.eiasiscon.security.user.entity.User;

@Document
public class Empresa extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
    private TpDoc tpDoc;
    private String numDoc;
    private String IE;
    @NotNull
    private String nome;
    private String fone;
    private String email;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    @NotNull
    @DBRef
    private Municipio municipio;
    @NotNull
    @DBRef
    private Pais pais;
    private String fantasia;
    private String IEST;
    private CRT crt;
    @DBRef
    private Collection<User> users;
    
	public TpDoc getTpDoc() {
		return tpDoc;
	}
	public void setTpDoc(TpDoc tpDoc) {
		this.tpDoc = tpDoc;
	}
	public String getNumDoc() {
		return numDoc;
	}
	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}
	public String getIE() {
		return IE;
	}
	public void setIE(String iE) {
		IE = iE;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public String getFantasia() {
		return fantasia;
	}
	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}
	public String getIEST() {
		return IEST;
	}
	public void setIEST(String iEST) {
		IEST = iEST;
	}
	public CRT getCrt() {
		return crt;
	}
	public void setCrt(CRT crt) {
		this.crt = crt;
	}
	public Collection<User> getUsers() {
		return users;
	}
	public void setUsers(Collection<User> users) {
		this.users = users;
	}
    
}
