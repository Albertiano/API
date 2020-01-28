package br.com.eiasiscon.notafiscal.pagamento;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum IndPag {
	A_VISTA("0", "A Vista"),
	A_PRAZO("1", "A Prazo");
	
	private IndPag(String value, String descricion){
        this.valor = value;
        this.setDescricao(descricion);
    }
	
	@JsonCreator
    public static IndPag create (@JsonProperty("valor") String value){
        for(IndPag v : values()) {
            if(value.equals(v.getValor())) {
                return v;
            }
        }
        return null;
    }
	
	private String valor;
	private String descricao;
	
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
