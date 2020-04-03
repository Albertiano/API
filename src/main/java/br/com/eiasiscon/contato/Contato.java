package br.com.eiasiscon.contato;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.empresa.Empresa;
import br.com.eiasiscon.municipio.Municipio;
import br.com.eiasiscon.pais.Pais;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Contato extends BaseEntity {
	
	private String codigo;
	@Enumerated(EnumType.STRING)
    private TpDoc tpDoc;
    private String numDoc;
    private String IE;
    @NotNull
    private String nome;
    private String fone;
    private String obs;
    private String email;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    @NotNull
    @ManyToOne
    private Municipio municipio;
    @NotNull
    @ManyToOne
    private Pais pais;
    @ManyToOne
    private Empresa empresa;
    private String contato;
    private boolean desabilitado;
    private boolean bloqueado;
    private String foneRes;
    private String celular;
    private String fantasia;
    private String ISUF;
    @Enumerated(EnumType.STRING)
    private IndIEDest indIEDest;
    
    private String lat;
    private String lng;
    
    private boolean clienteDistribuidor;
    private boolean clienteRevendedor;
    private boolean fornecedor;
    private boolean transportador;
}
