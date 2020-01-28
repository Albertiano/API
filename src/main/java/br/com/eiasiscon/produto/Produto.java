package br.com.eiasiscon.produto;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.empresa.Empresa;
import br.com.eiasiscon.produto.FornecedorProduto;
import br.com.eiasiscon.produto.tributacao.Tributacao;
import br.com.eiasiscon.produto.unidade.Unidade;

@Document
public class Produto  extends BaseEntity {

	private static final long serialVersionUID = -2413283511568165078L;
	@DBRef
    private Empresa empresa;
	private String codigo;
    private String referencia;
    private String descricao;
    @DBRef
    private Unidade unidade;
    private boolean desativado;
    private String ncm;
    private String cest;
    private BigDecimal precoCusto;
    private BigDecimal mLucro;
    private BigDecimal precoVenda;
    private BigDecimal descMax;
    private BigDecimal pesoBruto;
    private BigDecimal pesoLiquido;
    private BigDecimal estoqueMin;
    private BigDecimal estoque;    
    private String localizacao;
    @DBRef
    private List<FornecedorProduto> fornecedores;
    @DBRef
    private Tributacao tributacao;
    private String extipi;
    private String genero;
    private String cEan;
    private String cEanTrib;
    @DBRef
    private Unidade utrib;
    private BigDecimal vuntrib;
    
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public boolean isDesativado() {
		return desativado;
	}
	public void setDesativado(boolean desativado) {
		this.desativado = desativado;
	}
	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}
	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}
	public BigDecimal getmLucro() {
		return mLucro;
	}
	public void setmLucro(BigDecimal mLucro) {
		this.mLucro = mLucro;
	}
	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}
	public BigDecimal getDescMax() {
		return descMax;
	}
	public void setDescMax(BigDecimal descMax) {
		this.descMax = descMax;
	}
	public BigDecimal getPesoBruto() {
		return pesoBruto;
	}
	public void setPesoBruto(BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}
	public BigDecimal getPesoLiquido() {
		return pesoLiquido;
	}
	public void setPesoLiquido(BigDecimal pesoLiquido) {
		this.pesoLiquido = pesoLiquido;
	}
	public BigDecimal getEstoqueMin() {
		return estoqueMin;
	}
	public void setEstoqueMin(BigDecimal estoqueMin) {
		this.estoqueMin = estoqueMin;
	}
	public BigDecimal getEstoque() {
		return estoque;
	}
	public void setEstoque(BigDecimal estoque) {
		this.estoque = estoque;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public List<FornecedorProduto> getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(List<FornecedorProduto> fornecedores) {
		this.fornecedores = fornecedores;
	}
	public Tributacao getTributacao() {
		return tributacao;
	}
	public void setTributacao(Tributacao tributacao) {
		this.tributacao = tributacao;
	}
	public String getExtipi() {
		return extipi;
	}
	public void setExtipi(String extipi) {
		this.extipi = extipi;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getcEan() {
		return cEan;
	}
	public void setcEan(String cEan) {
		this.cEan = cEan;
	}
	public String getcEanTrib() {
		return cEanTrib;
	}
	public void setcEanTrib(String cEanTrib) {
		this.cEanTrib = cEanTrib;
	}
	public Unidade getUtrib() {
		return utrib;
	}
	public void setUtrib(Unidade utrib) {
		this.utrib = utrib;
	}
	public BigDecimal getVuntrib() {
		return vuntrib;
	}
	public void setVuntrib(BigDecimal vuntrib) {
		this.vuntrib = vuntrib;
	}
	public String getNcm() {
		return ncm;
	}
	public void setNcm(String ncm) {
		this.ncm = ncm;
	}
	public String getCest() {
		return cest;
	}
	public void setCest(String cest) {
		this.cest = cest;
	}
}
