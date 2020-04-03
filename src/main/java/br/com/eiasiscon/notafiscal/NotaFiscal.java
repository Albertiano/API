package br.com.eiasiscon.notafiscal;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.eiasiscon.base.BaseEntity;
import br.com.eiasiscon.contato.Contato;
import br.com.eiasiscon.empresa.Empresa;
import br.com.eiasiscon.municipio.UF;
import br.com.eiasiscon.notafiscal.cobranca.Cobranca;
import br.com.eiasiscon.notafiscal.ide.FinNFe;
import br.com.eiasiscon.notafiscal.ide.IdDest;
import br.com.eiasiscon.notafiscal.ide.IndFinal;
import br.com.eiasiscon.notafiscal.ide.IndPres;
import br.com.eiasiscon.notafiscal.ide.TpImp;
import br.com.eiasiscon.notafiscal.ide.TpNF;
import br.com.eiasiscon.notafiscal.ide.ref.NFref;
import br.com.eiasiscon.notafiscal.item.ItemNotaFiscal;
import br.com.eiasiscon.notafiscal.pagamento.Pagamento;
import br.com.eiasiscon.notafiscal.total.Total;
import br.com.eiasiscon.notafiscal.transporte.Transporte;
import br.com.eiasiscon.pdv.config.ConfigPdv;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper=false)
@Data
@Builder(toBuilder=true)
public class NotaFiscal extends BaseEntity {

	@ManyToOne
    private Empresa empresa;
	@ManyToOne
    private ConfigPdv pdv;
	private String sitNfe;
	private String versao;
	private String chave;
	@Enumerated(EnumType.STRING)
	private UF uf;
	private String cNF;
	private String natOp;
	private String mod;
	private Integer serie;
	private Integer numero;
	private Date dhEmi;
	private Date dhSaiEnt;
	@Enumerated(EnumType.STRING)
	private TpNF tpNF;
	@Enumerated(EnumType.STRING)
	private IdDest  idDest;
	private String munFG;
	@Enumerated(EnumType.STRING)
	private TpImp tpImp;
	private String tpEmis;
	private int cDV;
	private String tpAmb;
	@Enumerated(EnumType.STRING)
	private FinNFe finNFe;
	@Enumerated(EnumType.STRING)
	private IndFinal indFinal;
	@Enumerated(EnumType.STRING)
	private IndPres indPres;
	private final String procEmi = "0";
	private final String verProc = "4.00";
	private Date dhCont;	
	private String xJust;
	@OneToMany(cascade = CascadeType.ALL)
	private List<NFref> NFRef;
	@ManyToOne
	private Contato dest;
	// private Local retirada;
	// private Local entrega;
	// private List<AutXML> autXML;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ItemNotaFiscal> itens;
	@OneToOne(cascade = CascadeType.ALL)
	private Total total;
	@OneToOne(cascade = CascadeType.ALL)
	private Transporte transp;
	@OneToOne(cascade = CascadeType.ALL)
	private Cobranca cobr;
	@OneToOne(cascade = CascadeType.ALL)
	private Pagamento pag;
	@OneToOne(cascade = CascadeType.ALL)
	private InfAdicionais infAdic;
	@Lob
	private String xml;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ProcEventoNFe> procEventoNFe;
}
