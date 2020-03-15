package br.com.eiasiscon.notafiscal.cobranca;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.eiasiscon.base.BaseEntity;

@Entity
public class Cobranca extends BaseEntity {
	@OneToOne
	private Fatura fat;
	@OneToMany
	private List<Duplicata> dup;
	
	public Fatura getFat() {
		return fat;
	}
	public void setFat(Fatura fat) {
		this.fat = fat;
	}
	public List<Duplicata> getDup() {
		return dup;
	}
	public void setDup(List<Duplicata> dup) {
		this.dup = dup;
	}
}
