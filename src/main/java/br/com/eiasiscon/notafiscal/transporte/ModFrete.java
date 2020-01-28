package br.com.eiasiscon.notafiscal.transporte;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ModFrete {
	EMITENTE("0","Por conta do emitente"),
	DESTINATARIO("1","Por conta do destinatário/remetente"),
	TERCEIROS("2","Por conta de terceiros"),
	SEM_FRETE("9","Sem frete");
	
	private String valor;
    private String descricao;

	private ModFrete(String value, String descricion){
        this.valor = value;
        this.setDescricao(descricion);
    }
    
    @JsonCreator
    public static ModFrete create (@JsonProperty("valor") String value){
        for(ModFrete v : values()) {
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