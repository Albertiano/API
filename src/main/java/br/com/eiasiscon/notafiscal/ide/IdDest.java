package br.com.eiasiscon.notafiscal.ide;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum IdDest {
	INTERNA("1","Operação interna"),
	INTERESTADUAL("2","Operação interestadual"),
	EXTERIOR("3","Operação com exterior");
	
	private String valor;
    private String descricao;

	private IdDest(String value, String descricion){
        this.valor = value;
        this.setDescricao(descricion);
    }
    
    @JsonCreator
    public static IdDest create (@JsonProperty("valor") String value){
        for(IdDest v : values()) {
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