package br.com.eiasiscon.municipio;

import java.io.Serializable;
import javax.persistence.Entity;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.municipio.UF;

@Entity
public class Municipio extends BaseEntity implements Serializable{
    
	private static final long serialVersionUID = 1L;
		
    private int cMun;
    private String xMun;
    private UF uf;
    
    public int getcMun() {
		return cMun;
	}

	public void setcMun(int cMun) {
		this.cMun = cMun;
	}

	public String getxMun() {
		return xMun;
	}

	public void setxMun(String xMun) {
		this.xMun = xMun;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		result = prime * result + cMun;
		result = prime * result + ((xMun == null) ? 0 : xMun.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Municipio other = (Municipio) obj;
		if (uf != other.uf)
			return false;
		if (cMun != other.cMun)
			return false;
		if (xMun == null) {
			if (other.xMun != null)
				return false;
		} else if (!xMun.equals(other.xMun))
			return false;
		return true;
	}
    
}
