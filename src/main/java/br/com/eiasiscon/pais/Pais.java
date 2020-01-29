package br.com.eiasiscon.pais;

import java.io.Serializable;
import javax.persistence.Entity;

import br.com.eiasiscon.base.BaseEntity;

@Entity
public class Pais extends BaseEntity  implements Serializable{
    
	private static final long serialVersionUID = 1L;
	private int cPais;
    private String xPais;
    
	public Pais(int cPais, String xPais) {
		this.cPais = cPais;
		this.xPais = xPais;
	}
	public Pais() {
	}
	public int getcPais() {
		return cPais;
	}
	public void setcPais(int cPais) {
		this.cPais = cPais;
	}
	public String getxPais() {
		return xPais;
	}
	public void setxPais(String xPais) {
		this.xPais = xPais;
	}
 
}