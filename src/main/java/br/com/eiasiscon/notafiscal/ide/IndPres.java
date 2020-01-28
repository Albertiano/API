package br.com.eiasiscon.notafiscal.ide;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum IndPres {
	NAO_APLICAVEL("0", "Não se aplica"),
	PRESENCIAL("1", "Presencial"),
	INTERNET("2", "Internet"),
	TELEATENDIMENTO("3", "Teleatendimento"),
	NFCe_DOMICILIO("4", "NFC-e em operação com entrega a domicílio"),
	FORA_ESTABELECIMENTO("5", "Operação presencial, fora do estabelecimento"),
	OUTRAS("9", "Outras");

	private String valor;
    private String descricao;

	private IndPres(String value, String descricion){
        this.valor = value;
        this.setDescricao(descricion);
    }
    
    @JsonCreator
    public static IndPres create (@JsonProperty("valor") String value){
        for(IndPres v : values()) {
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
