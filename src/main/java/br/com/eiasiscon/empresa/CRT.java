package br.com.eiasiscon.empresa;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CRT {
	SIMPLES_NACIONAL("1","Simples Nacional"),
	SIMPLES_NAC_EXC_SUBLIMETE("2","Simples Nacional, excesso sublimite de receita bruta"),
	NORMAL("3","Regime Normal");
	
	private String value;
	private String desc;
	
	private CRT(String v, String d) {
		this.value = v;
		this.desc = d;
	}
	public String getValue() {
		return value;
	}
	public String getDesc() {
		return desc;
	}
	
	@JsonCreator
    public static CRT create (@JsonProperty("value") String value){
        for(CRT v : values()) {
            if(value.equals(v.getValue())) {
                return v;
            }
        }
        return null;
    }
}
