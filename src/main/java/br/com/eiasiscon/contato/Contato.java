package br.com.eiasiscon.contato;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.contato.IndIEDest;
import br.com.eiasiscon.contato.TpDoc;
import br.com.eiasiscon.empresa.Empresa;
import br.com.eiasiscon.municipio.Municipio;
import br.com.eiasiscon.pais.Pais;

@Document
public class Contato extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String codigo;
    private TpDoc tpDoc;
    private String numDoc;
    private String IE;
    @NotNull
    private String nome;
    private String fone;
    private String obs;
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
    @DBRef
    private Empresa empresa;
    private String contato;
    private boolean desabilitado;
    private boolean bloqueado;
    private String foneRes;
    private String celular;
    private String fantasia;
    private String ISUF;
    private IndIEDest indIEDest;
    
    private String lat;
    private String lng;
    
    private boolean clienteDistribuidor;
    private boolean clienteRevendedor;
    private boolean fornecedor;
    private boolean transportador;

    public Contato() {

    }

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

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

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public boolean isDesabilitado() {
		return desabilitado;
	}

	public void setDesabilitado(boolean desabilitado) {
		this.desabilitado = desabilitado;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public String getFoneRes() {
		return foneRes;
	}

	public void setFoneRes(String foneRes) {
		this.foneRes = foneRes;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	public String getISUF() {
		return ISUF;
	}

	public void setISUF(String iSUF) {
		ISUF = iSUF;
	}

	public IndIEDest getIndIEDest() {
		return indIEDest;
	}

	public void setIndIEDest(IndIEDest indIEDest) {
		this.indIEDest = indIEDest;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public boolean isClienteDistribuidor() {
		return clienteDistribuidor;
	}

	public void setClienteDistribuidor(boolean clienteDistribuidor) {
		this.clienteDistribuidor = clienteDistribuidor;
	}

	public boolean isClienteRevendedor() {
		return clienteRevendedor;
	}

	public void setClienteRevendedor(boolean clienteRevendedor) {
		this.clienteRevendedor = clienteRevendedor;
	}

	public boolean isFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(boolean fornecedor) {
		this.fornecedor = fornecedor;
	}

	public boolean isTransportador() {
		return transportador;
	}

	public void setTransportador(boolean transportador) {
		this.transportador = transportador;
	}
}
