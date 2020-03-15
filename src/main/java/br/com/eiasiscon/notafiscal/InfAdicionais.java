package br.com.eiasiscon.notafiscal;

import javax.persistence.Entity;

import br.com.eiasiscon.base.BaseEntity;

@Entity
public class InfAdicionais extends BaseEntity {
	
	private String infAdFisco;
    private String infCpl;
    // private List<ProcRef> procRef;
    
	public String getInfAdFisco() {
		return infAdFisco;
	}
	public void setInfAdFisco(String infAdFisco) {
		this.infAdFisco = infAdFisco;
	}
	public String getInfCpl() {
		return infCpl;
	}
	public void setInfCpl(String infCpl) {
		this.infCpl = infCpl;
	}

}
