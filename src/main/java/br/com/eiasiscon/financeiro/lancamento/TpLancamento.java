package br.com.eiasiscon.financeiro.lancamento;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TpLancamento {
	CREDITO("C", "Crédito"), DEBITO("D", "Débito");

	private String valor;
	private String descricao;

	private TpLancamento(String value, String descricion) {
		this.valor = value;
		this.descricao = descricion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@JsonCreator
	public static TpLancamento create(@JsonProperty("valor") String value) {
		for (TpLancamento v : values()) {
			if (value.equals(v.getValor())) {
				return v;
			}
		}
		return null;
	}
}
