package br.com.eiasiscon.financeiro.planocontas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TpConta {
	CREDITO("C", "Crédito"), DEBITO("D", "Débito"), NEUTRO("N", "Neutro");

	private String valor;
	private String descricao;

	private TpConta(String value, String descricion) {
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
	public static TpConta create(@JsonProperty("valor") String value) {
		for (TpConta v : values()) {
			if (value.equals(v.getValor())) {
				return v;
			}
		}
		return null;
	}
}
