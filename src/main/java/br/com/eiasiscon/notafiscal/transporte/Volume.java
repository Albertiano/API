package br.com.eiasiscon.notafiscal.transporte;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import br.com.eiasiscon.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Volume extends BaseEntity {
	
	private BigDecimal qVol;
	private String esp;
	private String marca;
	private String nVol;
	private BigDecimal pesoL;
    private BigDecimal pesoB;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Lacre> lacres;
}
