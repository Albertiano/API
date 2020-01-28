package br.com.eiasiscon.notafiscal;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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

@Document
public class NotaFiscal  extends BaseEntity {

	private static final long serialVersionUID = 225676107701479209L;
	
	@DBRef
    private Empresa empresa;
	@DBRef
    private ConfigPdv pdv;
	private String sitNfe;
	private String versao;
	private String chave;
	private UF uf;
	private String cNF;
	private String natOp;
	private String mod;
	private Integer serie;
	private Integer numero;
	private Date dhEmi;
	private Date dhSaiEnt;
	private TpNF tpNF;
	private IdDest  idDest;
	private String munFG;
	private TpImp tpImp;
	private String tpEmis;
	private int cDV;
	private String tpAmb;
	private FinNFe finNFe;
	private IndFinal indFinal;
	private IndPres indPres;
	private String procEmi = "0";
	private String verProc = "4.00";
	private Date dhCont;	
	private String xJust;
	@DBRef
	private List<NFref> NFRef;
	private Empresa emitente;
	private Contato dest;
	// private Local retirada;
	// private Local entrega;
	// private List<AutXML> autXML;
	private List<ItemNotaFiscal> itens;
	private Total total;
	private Transporte transp;
	private Cobranca cobr;
	private Pagamento pag;
	private InfAdicionais infAdic;
	
	private String xml;
	private List<String> procEventoNFe;
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getSitNfe() {
		return sitNfe;
	}
	public void setSitNfe(String sitNfe) {
		this.sitNfe = sitNfe;
	}
	public String getVersao() {
		return versao;
	}
	public void setVersao(String versao) {
		this.versao = versao;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public UF getUf() {
		return uf;
	}
	public void setUf(UF uf) {
		this.uf = uf;
	}
	public String getcNF() {
		return cNF;
	}
	public void setcNF(String cNF) {
		this.cNF = cNF;
	}
	public String getNatOp() {
		return natOp;
	}
	public void setNatOp(String natOp) {
		this.natOp = natOp;
	}
	public String getMod() {
		return mod;
	}
	public void setMod(String mod) {
		this.mod = mod;
	}
	public Integer getSerie() {
		return serie;
	}
	public void setSerie(Integer serie) {
		this.serie = serie;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Date getDhEmi() {
		return dhEmi;
	}
	public void setDhEmi(Date dhEmi) {
		this.dhEmi = dhEmi;
	}
	public Date getDhSaiEnt() {
		return dhSaiEnt;
	}
	public void setDhSaiEnt(Date dhSaiEnt) {
		this.dhSaiEnt = dhSaiEnt;
	}
	public TpNF getTpNF() {
		return tpNF;
	}
	public void setTpNF(TpNF tpNF) {
		this.tpNF = tpNF;
	}
	public IdDest getIdDest() {
		return idDest;
	}
	public void setIdDest(IdDest idDest) {
		this.idDest = idDest;
	}
	public String getMunFG() {
		return munFG;
	}
	public void setMunFG(String munFG) {
		this.munFG = munFG;
	}
	public TpImp getTpImp() {
		return tpImp;
	}
	public void setTpImp(TpImp tpImp) {
		this.tpImp = tpImp;
	}
	public String getTpEmis() {
		return tpEmis;
	}
	public void setTpEmis(String tpEmis) {
		this.tpEmis = tpEmis;
	}
	public int getcDV() {
		return cDV;
	}
	public void setcDV(int cDV) {
		this.cDV = cDV;
	}
	public String getTpAmb() {
		return tpAmb;
	}
	public void setTpAmb(String tpAmb) {
		this.tpAmb = tpAmb;
	}
	public FinNFe getFinNFe() {
		return finNFe;
	}
	public void setFinNFe(FinNFe finNFe) {
		this.finNFe = finNFe;
	}
	public IndFinal getIndFinal() {
		return indFinal;
	}
	public void setIndFinal(IndFinal indFinal) {
		this.indFinal = indFinal;
	}
	public IndPres getIndPres() {
		return indPres;
	}
	public void setIndPres(IndPres indPres) {
		this.indPres = indPres;
	}
	public String getProcEmi() {
		return procEmi;
	}
	public void setProcEmi(String procEmi) {
		this.procEmi = procEmi;
	}
	public String getVerProc() {
		return verProc;
	}
	public void setVerProc(String verProc) {
		this.verProc = verProc;
	}
	public Date getDhCont() {
		return dhCont;
	}
	public void setDhCont(Date dhCont) {
		this.dhCont = dhCont;
	}
	public String getxJust() {
		return xJust;
	}
	public void setxJust(String xJust) {
		this.xJust = xJust;
	}
	public List<NFref> getNFRef() {
		return NFRef;
	}
	public void setNFRef(List<NFref> nFRef) {
		NFRef = nFRef;
	}
	public Empresa getEmitente() {
		return emitente;
	}
	public void setEmitente(Empresa emitente) {
		this.emitente = emitente;
	}
	public Contato getDest() {
		return dest;
	}
	public void setDest(Contato dest) {
		this.dest = dest;
	}
	public List<ItemNotaFiscal> getItens() {
		return itens;
	}
	public void setItens(List<ItemNotaFiscal> itens) {
		this.itens = itens;
	}
	public Total getTotal() {
		return total;
	}
	public void setTotal(Total total) {
		this.total = total;
	}
	public Transporte getTransp() {
		return transp;
	}
	public void setTransp(Transporte transp) {
		this.transp = transp;
	}
	public Cobranca getCobr() {
		return cobr;
	}
	public void setCobr(Cobranca cobr) {
		this.cobr = cobr;
	}
	public Pagamento getPag() {
		return pag;
	}
	public void setPag(Pagamento pag) {
		this.pag = pag;
	}
	public InfAdicionais getInfAdic() {
		return infAdic;
	}
	public void setInfAdic(InfAdicionais infAdic) {
		this.infAdic = infAdic;
	}
	public List<String> getProcEventoNFe() {
		return procEventoNFe;
	}
	public void setProcEventoNFe(List<String> procEventoNFe) {
		this.procEventoNFe = procEventoNFe;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	public ConfigPdv getPdv() {
		return pdv;
	}
	public void setPdv(ConfigPdv pdv) {
		this.pdv = pdv;
	}

}
