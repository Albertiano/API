package br.com.eiasiscon.produto;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.DBRef;

import br.com.eiasiscon.contato.Contato;
import br.com.eiasiscon.produto.unidade.Unidade;

public class FornecedorProduto {
	
	@DBRef
	private Contato fornecedor;
	private String codFornecedor;
	@DBRef
	private Unidade unidFornecedor;
	private BigDecimal fatorConversao;
	
	public Contato getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Contato fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getCodFornecedor() {
		return codFornecedor;
	}
	public void setCodFornecedor(String codFornecedor) {
		this.codFornecedor = codFornecedor;
	}
	public Unidade getUnidFornecedor() {
		return unidFornecedor;
	}
	public void setUnidFornecedor(Unidade unidFornecedor) {
		this.unidFornecedor = unidFornecedor;
	}
	public BigDecimal getFatorConversao() {
		return fatorConversao;
	}
	public void setFatorConversao(BigDecimal fatorConversao) {
		this.fatorConversao = fatorConversao;
	}
}
