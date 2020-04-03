package br.com.eiasiscon.empresa;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.contato.TpDoc;
import br.com.eiasiscon.municipio.Municipio;
import br.com.eiasiscon.pais.Pais;
import br.com.eiasiscon.security.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Empresa extends BaseEntity {
	
	@Enumerated(EnumType.STRING)
    private TpDoc tpDoc;
    private String numDoc;
    private String IE;
    @NotNull
    private String nome;
    private String fone;
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
    private String fantasia;
    private String IEST;
    @Enumerated(EnumType.STRING)
    private CRT crt;
    @ManyToMany
    private Collection<User> users;
}
