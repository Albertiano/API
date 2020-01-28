package br.com.eiasiscon.notafiscal.pagamento;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TpPag {
	DINHEIRO("01", "Dinheiro"),
	CHEQUE("02","Cheque"),
	CARTAO_CREDITO("03","Cartão de Crédito"),
	CARTAO_DEBITO("04","Cartão de Débito"),
	CREDITO("05","Crédito Loja"),
	VALE_ALIMENTACAO("10", "Vale Alimentação"),
	VALE_REFEICAO("11", "Vale Refeição"),
	VALE_PRESENTE("12", "Vale Presente"),
	VALE_COMBUSTIVEL("13", "Vale Combustível"),
	SEM_PAGAMENTO("90", "Sem pagamento"),
	OUTROS("99","Outros");
	
	private String valor;
    private String descricao;

	private TpPag(String value, String descricion){
        this.valor = value;
        this.setDescricao(descricion);
    }
    
    @JsonCreator
    public static TpPag create (@JsonProperty("valor") String value){
        for(TpPag v : values()) {
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