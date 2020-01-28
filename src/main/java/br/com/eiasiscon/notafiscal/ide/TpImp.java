package br.com.eiasiscon.notafiscal.ide;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TpImp {
	RETRATO("1", "RETRATO"),
	PAISAGEM("2", "PAISAGEM"),
	SIMPLIFICADO("3", "SIMPLIFICADO"),
	NFCe("4", "NFCe"),
	NFCe_MENS_ELETRONICA("5", "NFCe_MENS_ELETRONICA");

	private String valor;
    private String descricao;

	private TpImp(String value, String descricion){
        this.valor = value;
        this.setDescricao(descricion);
    }
    
    @JsonCreator
    public static TpImp create (@JsonProperty("valor") String value){
        for(TpImp v : values()) {
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