package br.com.eiasiscon.notafiscal.ide;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TpNF {
	ENTRADA("0", "Entrada"), SAIDA("1", "Saída");

	private String valor;
    private String descricao;

	private TpNF(String value, String descricion){
        this.valor = value;
        this.setDescricao(descricion);
    }
    
    @JsonCreator
    public static TpNF create (@JsonProperty("valor") String value){
        for(TpNF v : values()) {
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