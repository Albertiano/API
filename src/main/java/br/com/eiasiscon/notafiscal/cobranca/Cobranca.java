package br.com.eiasiscon.notafiscal.cobranca;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.eiasiscon.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Cobranca extends BaseEntity {
	@OneToOne
	private Fatura fat;
	@OneToMany
	private List<Duplicata> dup;
}
