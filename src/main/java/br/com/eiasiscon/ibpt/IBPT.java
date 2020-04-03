package br.com.eiasiscon.ibpt;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;
import br.com.eiasiscon.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "IBPT")
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class IBPT extends BaseEntity {

	private String codigo;
	private String tipo;
	private String descricao;
	private double nacionalFederal;
	private double importadosFederal;
	private double estadual;
	private double municipal;
	private LocalDate vigenciaInicio;
	private LocalDate vigenciaFim;
	private String chave;
	private String versao;
	private String fonte;	
}
