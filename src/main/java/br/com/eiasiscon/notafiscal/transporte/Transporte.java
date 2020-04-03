package br.com.eiasiscon.notafiscal.transporte;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.contato.Contato;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Transporte extends BaseEntity {
	
	@Enumerated(EnumType.STRING)
	private ModFrete modFrete;
	@ManyToOne
	private Contato transporta;
	@OneToOne(cascade = CascadeType.ALL)
	private Veiculo veicTransp;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Veiculo> reboque;
    private String vagao;
    private String balsa;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Volume> vol;
}
