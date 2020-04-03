package br.com.eiasiscon.produto.tributacao;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.empresa.Empresa;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Tributacao  extends BaseEntity {

	@ManyToOne
    private Empresa empresa;
	String nome;
	String descricao;
	@OneToMany(cascade = CascadeType.ALL)
	List<Destino> destinos;
}
