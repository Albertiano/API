package br.com.eiasiscon.notafiscal.ide;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FinNFe {
	NORMAL("1", "Normal"),
	COMPLEMENTAR("2", "Complementar"),
	AJUSTE("3", "Ajuste"),
	DEVOLUCAO_RETORNO("4", "Devolução/Retorno");

	private String valor;
    private String descricao;

	private FinNFe(String value, String descricion){
        this.valor = value;
        this.setDescricao(descricion);
    }
    
    @JsonCreator
    public static FinNFe create (@JsonProperty("valor") String value){
        for(FinNFe v : values()) {
            if(value.equals(v.getValor())) {
                return v;
            }
        }
        return null;
    }
    
    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}