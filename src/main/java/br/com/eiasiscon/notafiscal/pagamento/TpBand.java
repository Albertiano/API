package br.com.eiasiscon.notafiscal.pagamento;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum TpBand {
	VISA("01", "Visa"),
	MASTERCARD("02", "Mastercard"),
	AMERICAN_EXPRESS("03", "American Express"),
	SOROCRED("04", "Sorocred"),
	OUTROS("99", "Outros");
	
	private String valor;
    private String descricao;

	private TpBand(String value, String descricion){
        this.valor = value;
        this.setDescricao(descricion);
    }
    
    @JsonCreator
    public static TpBand create (@JsonProperty("valor") String value){
        for(TpBand v : values()) {
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