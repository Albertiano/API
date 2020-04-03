package br.com.eiasiscon.produto;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.empresa.Empresa;
import br.com.eiasiscon.produto.tributacao.Tributacao;
import br.com.eiasiscon.produto.unidade.Unidade;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class Produto  extends BaseEntity {
	
	@ManyToOne
    private Empresa empresa;
	private String codigo;
    private String referencia;
    private String descricao;
    @ManyToOne
    private Unidade unidade;
    private boolean desativado;
    private String ncm;
    private String cest;
    private BigDecimal precoCusto;
    private BigDecimal mLucro;
    private BigDecimal precoVenda;
    private BigDecimal descMax;
    private BigDecimal pesoBruto;
    private BigDecimal pesoLiquido;
    private BigDecimal estoqueMin;
    private BigDecimal estoque;    
    private String localizacao;
    @ManyToOne
    private Tributacao tributacao;
    private String extipi;
    private String genero;
    private String cEan;
    private String cEanTrib;
    @ManyToOne
    private Unidade utrib;
    private BigDecimal vuntrib;
}
