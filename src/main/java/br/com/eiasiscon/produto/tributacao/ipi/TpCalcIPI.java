/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eiasiscon.produto.tributacao.ipi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TpCalcIPI {
    
    NA("00", ""),
    ALIQUOTA("01", "Percentual"),
    UNIDADE("02", "Em Valor");
    
    TpCalcIPI(String value, String descricion){
        this.valor = value;
        this.descricao = descricion;
        
    }
    
    private String valor;
    private String descricao;

    @Override
    public String toString() {
        return getDescricao();
    }
    
    @JsonCreator
    public static TpCalcIPI create (@JsonProperty("valor") String value){
        for(TpCalcIPI v : values()) {
            if(value.equals(v.getValor())) {
                return v;
            }
        }
        return null;
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
    
}
