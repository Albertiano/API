package br.com.eiasiscon.nfe.common;

import java.math.BigDecimal;

import br.com.eiasiscon.contato.Contato;
import br.com.eiasiscon.contato.IndIEDest;
import br.com.eiasiscon.contato.TpDoc;
import br.com.eiasiscon.empresa.Empresa;
import br.com.eiasiscon.notafiscal.NotaFiscal;
import br.com.eiasiscon.notafiscal.cobranca.Cobranca;
import br.com.eiasiscon.notafiscal.cobranca.Duplicata;
import br.com.eiasiscon.notafiscal.item.DetalheFiscal;
import br.com.eiasiscon.notafiscal.item.ItemNotaFiscal;
import br.com.eiasiscon.notafiscal.pagamento.DetPag;
import br.com.eiasiscon.notafiscal.transporte.ModFrete;
import br.com.eiasiscon.notafiscal.transporte.Transporte;
import br.com.eiasiscon.notafiscal.transporte.Veiculo;
import br.com.eiasiscon.produto.tributacao.cofins.CST_COFINS;
import br.com.eiasiscon.produto.tributacao.cofins.TpCalcCOFINS;
import br.com.eiasiscon.produto.tributacao.icms.CST_ICMS;
import br.com.eiasiscon.produto.tributacao.ipi.CST_IPI;
import br.com.eiasiscon.produto.tributacao.ipi.IPI;
import br.com.eiasiscon.produto.tributacao.ipi.TpCalcIPI;
import br.com.eiasiscon.produto.tributacao.pis.CST_PIS;
import br.com.eiasiscon.produto.tributacao.pis.TpCalcPIS;
import br.com.eiasiscon.util.ConversorBigDecimal;
import br.com.eiasiscon.util.ConversorDate;
import br.com.swconsultoria.nfe.schema_4.enviNFe.ObjectFactory;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnderEmi;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEndereco;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TIpi;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TIpi.IPINT;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TIpi.IPITrib;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Cobr;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Cobr.Dup;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Cobr.Fat;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Dest;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSAliq;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINS.COFINSQtde;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.COFINSST;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS00;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS10;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS20;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS30;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS40;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS51;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS60;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS70;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMS90;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSPart;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN101;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN102;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN201;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN202;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN900;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISAliq;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISNT;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISOutr;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PIS.PISQtde;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Imposto.PISST;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Det.Prod;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Emit;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Ide;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Ide.NFref;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.InfAdic;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Pag;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Total.ICMSTot;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp.Transporta;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe.InfNFe.Transp.Vol;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUf;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TUfEmi;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TVeiculo;

public class NFeConversor {

    private static String ambiente;

    public static TNFe getnFe(NotaFiscal nf, String ambient) {
        ambiente = ambient;
        TNFe nFe = new TNFe();
        nFe.setInfNFe(getInfNFe(nf));
        return nFe;
    }

    private static InfNFe getInfNFe(NotaFiscal nf) {
        InfNFe infNFe = new InfNFe();
        infNFe.setId("NFe" + nf.getChave());
        infNFe.setVersao(nf.getVersao().toString());

        infNFe.setIde(dadosDeIdentificacao(nf));
        infNFe.setEmit(dadosDoEmitente(nf));
        infNFe.setDest(dadosDoDestinatario(nf));
        int item = 0;
        for (ItemNotaFiscal i : nf.getItens()) {
        	item++;
        	i.setNItem(item);
            infNFe.getDet().add(dadosDoProduto(i));
        }
        infNFe.setTotal(totaisDaNFe(nf));
        infNFe.setTransp(dadosDoTransporte(nf));
        infNFe.setInfAdic(informacoesAdicionais(nf));
        
        infNFe.setPag(getPag(nf));

        infNFe.setCobr(getCobr(nf));

        return infNFe;
    }

    private static Ide dadosDeIdentificacao(NotaFiscal nf) {
        Ide ide = new Ide();
        ide.setCUF(nf.getEmpresa().getMunicipio().getUf().getCUF());

        String dEmis = ConversorDate.retornaDataHora(nf.getDhEmi()).replaceAll("[^0-9]", "");
        
        ide.setCNF(dEmis.substring(0, 2) + dEmis.substring(8, 14));

        ide.setNatOp(nf.getNatOp().trim());
        ide.setMod(nf.getMod().toString());
        ide.setSerie(nf.getSerie().toString());
        ide.setNNF(nf.getNumero().toString());
        ide.setDhEmi(ConversorDate.retornaDataHoraNFe(nf.getDhEmi()));
        ide.setDhSaiEnt(ConversorDate.retornaDataHoraNFe(nf.getDhSaiEnt()));
        ide.setTpNF(nf.getTpNF().getValor());
        ide.setIdDest(nf.getIdDest().getValor());
        ide.setCMunFG(String.valueOf(nf.getEmpresa().getMunicipio().getCMun()));
        ide.setTpImp(nf.getTpImp().getValor());
        ide.setTpEmis(nf.getTpEmis());
        ide.setCDV(nf.getChave().substring(43));
        ide.setTpAmb(ambiente);
        ide.setFinNFe(nf.getFinNFe().getValor());
        ide.setIndFinal(nf.getIndFinal().getValor());
        ide.setIndPres(nf.getIndPres().getValor());
        ide.setProcEmi(nf.getProcEmi());
        ide.setVerProc("0");
        if (nf.getNFRef() != null) {
            for (br.com.eiasiscon.notafiscal.ide.ref.NFref nfRef : nf.getNFRef()) {
                NFref nRef = new NFref();
                nRef.setRefNFe(nfRef.getRefNFe().trim());
                ide.getNFref().add(nRef);
            }
        }
        return ide;
    }

    private static Emit dadosDoEmitente(NotaFiscal nf) {
        Empresa em = nf.getEmpresa();
        Emit emit = new Emit();
        if(em.getNumDoc()!=null){
        	if(em.getTpDoc().equals(TpDoc.CNPJ)){
        		emit.setCNPJ(em.getNumDoc().replaceAll("[^0-9]", ""));
        	}
        	if(em.getTpDoc().equals(TpDoc.CPF)){
        		emit.setCPF(em.getNumDoc().replaceAll("[^0-9]", ""));
        	}
        	
        }
        
        emit.setXNome(em.getNome().trim());
        if(em.getFantasia()!=null){
        	 emit.setXFant(em.getFantasia().trim());
        }
       
        TEnderEmi enderEmit = new TEnderEmi();
        enderEmit.setXLgr(em.getLogradouro().trim());
        enderEmit.setNro(em.getNumero().trim());
        enderEmit.setXBairro(em.getBairro().trim());
        if(em.getComplemento()!=null){
        	enderEmit.setXCpl(em.getComplemento().trim());
        }        
        enderEmit.setCMun(String.valueOf(em.getMunicipio().getCMun()));
        enderEmit.setXMun(em.getMunicipio().getXMun());
        enderEmit.setUF(TUfEmi.valueOf(em.getMunicipio().getUf().toString()));
        if (em.getCep() != null) {
            enderEmit.setCEP(em.getCep().replaceAll("[^0-9]", ""));
        }
        enderEmit.setCPais(String.valueOf(em.getPais().getCPais()));
        enderEmit.setXPais(em.getPais().getXPais());
        if (em.getFone() != null) {
            enderEmit.setFone(em.getFone().replaceAll("[^0-9]", ""));
        }
        emit.setEnderEmit(enderEmit);
        if(em.getIE()!=null){
        	emit.setIE(em.getIE().replaceAll("[^0-9]", ""));
        }
        
        emit.setCRT(em.getCrt().getValue());
        return emit;
    }

    private static Dest dadosDoDestinatario(NotaFiscal nf) {
        Contato c = nf.getDest();
        Dest dest = new Dest();
        if(c.getNumDoc()!=null){
        	if(c.getTpDoc() !=null) {
        		if(c.getTpDoc().equals(TpDoc.CNPJ)){
            		dest.setCNPJ(c.getNumDoc().replaceAll("[^0-9]", ""));
            	}
            	if(c.getTpDoc().equals(TpDoc.CPF)){
            		dest.setCPF(c.getNumDoc().replaceAll("[^0-9]", ""));
            	}
        	}        	
        }
        dest.setXNome(c.getNome().trim());

        TEndereco enderDest = new TEndereco();
        enderDest.setXLgr(c.getLogradouro().trim());
        if(c.getNumero() == null) {
        	c.setNumero("");
        }
        enderDest.setNro(c.getNumero().trim());
        if(c.getBairro() == null) {
        	enderDest.setXBairro("");
        } else {
        	enderDest.setXBairro(c.getBairro().trim());
        }
        
        if(c.getComplemento()!=null){
        	enderDest.setXCpl(c.getComplemento().trim());
        }
        if(c.getComplemento()==null){
        	enderDest.setXCpl(null);
        }else if(c.getComplemento().isEmpty()){
        	enderDest.setXCpl(null);
        }
        enderDest.setCMun(String.valueOf(c.getMunicipio().getCMun()));
        enderDest.setXMun(c.getMunicipio().getXMun());
        enderDest.setUF(TUf.valueOf(c.getMunicipio().getUf().toString()));
        
        enderDest.setCEP(c.getCep().replaceAll("[^0-9]", ""));
        enderDest.setCPais(String.valueOf(c.getPais().getCPais()));
        enderDest.setXPais(c.getPais().getXPais());
        if (c.getFone() != null) {
            enderDest.setFone(c.getFone().replaceAll("[^0-9]", ""));
        }
        dest.setEnderDest(enderDest);
        
        if (c.getIE() != null) {
        	String ie = c.getIE();
        	if(ie.toUpperCase().equals("ISENTO")) {
        		dest.setIndIEDest(IndIEDest.ISENTO.getValue());
        	} else if(ie.replaceAll("[^0-9]", "").length() > 0) {
        		dest.setIndIEDest(IndIEDest.CONTRIBUINTE.getValue());
        		dest.setIE(ie.replaceAll("[^0-9]", ""));
        	} else {
        		dest.setIndIEDest(IndIEDest.NAO_CONTRIBUINTE.getValue());
        	}
        } else{
            dest.setIndIEDest(IndIEDest.NAO_CONTRIBUINTE.getValue());
        }
        
        if(c.getEmail()!=null){
        	dest.setEmail(c.getEmail().trim());
        }
        
        return dest;
    }

    private static Det dadosDoProduto(ItemNotaFiscal i) {
        Det det = new Det();
        det.setNItem(String.valueOf(i.getNItem()));

        /**
         * Dados do Produro
         */
        Prod prod = new Prod();
        prod.setCProd(i.getProduto().getCodigo());
        prod.setCEAN(i.getProduto().getCEan());
        if(i.getProduto().getCEan()==null){
            prod.setCEAN("");
        }
        prod.setXProd(i.getProduto().getDescricao().trim());
        prod.setNCM(i.getProduto().getNcm());
        prod.setCEST((i.getProduto().getCest()));
        prod.setCFOP(i.getDetFiscal().getCfop());
        prod.setUCom(i.getProduto().getUnidade().getSigla());
        prod.setQCom(ConversorBigDecimal.paraStringNFeQuant(i.getQuantidade()));
        prod.setVUnCom(ConversorBigDecimal.paraStringNFePreco(i.getPrecoVenda()));
        prod.setVProd(ConversorBigDecimal.paraStringNFeValor(i.getSubtotal()));
        prod.setUTrib(i.getProduto().getUtrib().getSigla());
        prod.setQTrib(ConversorBigDecimal.paraStringNFeQuant(i.getDetFiscal().getqTrib()));
        prod.setCEANTrib(i.getProduto().getCEanTrib());
        if(i.getProduto().getCEanTrib() == null){
            prod.setCEANTrib("");
        }
        prod.setVUnTrib(ConversorBigDecimal.paraStringNFePreco(i.getDetFiscal().getVuntrib()));
        prod.setIndTot("1");
        det.setProd(prod);

        /**
         * Impostos da NF-e
         */
        Imposto imposto = new Imposto();
        ICMS icms = getICMS(i);

        TIpi ipi = getIPI(i);

        PIS pis = getPIS(i);

        PISST pisST = getPISST(i);

        COFINS cofins = getCOFINS(i);

        COFINSST cofinsSt = getcofinsST(i);

        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoIPI(ipi));
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoPIS(pis));
        if (pisST != null) {
            imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoPISST(pisST));
        }
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINS(cofins));
        if (cofinsSt != null) {
            imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINSST(cofinsSt));
        }
        
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoVTotTrib(ConversorBigDecimal.paraStringNFeValor(i.getDetFiscal().getvTotTrib()))); 

        det.setImposto(imposto);

        /**
         * Informações adicionais do Produto.
         */
        det.setInfAdProd(i.getIfAdic());

        return det;
    }

    private static ICMS getICMS(ItemNotaFiscal i) {
        DetalheFiscal d = i.getDetFiscal();
        ICMS icms = new ICMS();

        CST_ICMS ICMS_CST = d.getIcms().getCstICMS();
        switch (ICMS_CST) {
            case ICMS_00:
                ICMS00 _00 = new ICMS00();
                _00.setOrig(d.getIcms().getOrigem().getValor());
                _00.setCST(d.getIcms().getCstICMS().getValor());
                _00.setModBC(d.getIcms().getModBCICMS().getValor());
                _00.setVBC(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCICMS()));
                _00.setPICMS(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMS()));
                _00.setVICMS(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMS()));
                icms.setICMS00(_00);
                break;
            case ICMS_10:
                ICMS10 _10 = new ICMS10();
                _10.setOrig(d.getIcms().getOrigem().getValor());
                _10.setCST(d.getIcms().getCstICMS().getValor());
                _10.setModBC(d.getIcms().getModBCICMS().getValor());
                _10.setVBC(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCICMS()));
                _10.setPICMS(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMS()));
                _10.setVICMS(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMS()));
                _10.setModBCST(d.getIcms().getModBCST().getValor());
                _10.setPMVAST(d.getIcms().getPMVAST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPMVAST()) : null);
                _10.setPRedBCST(d.getIcms().getPRedBCST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCST()) : null);
                _10.setVBCST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCST()));
                _10.setPICMSST(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMSST()));
                _10.setVICMSST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMSST()));
                icms.setICMS10(_10);
                break;
            case ICMS_20:
                ICMS20 _20 = new ICMS20();
                _20.setOrig(d.getIcms().getOrigem().getValor());
                _20.setCST(d.getIcms().getCstICMS().getValor());
                _20.setModBC(d.getIcms().getModBCICMS().getValor());
                _20.setVBC(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCICMS()));
                _20.setPICMS(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMS()));
                _20.setVICMS(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMS()));
                _20.setVICMSDeson(d.getIcms().getVICMSDeson().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getVICMSDeson()) : null);
                _20.setMotDesICMS(d.getIcms().getMotDesICMS().getValor());
                icms.setICMS20(_20);
                break;
            case ICMS_30:
                ICMS30 _30 = new ICMS30();
                _30.setOrig(d.getIcms().getOrigem().getValor());
                _30.setCST(d.getIcms().getCstICMS().getValor());
                _30.setVICMSDeson(d.getIcms().getVICMSDeson().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getVICMSDeson()) : null);
                _30.setMotDesICMS(d.getIcms().getMotDesICMS().getValor());
                _30.setModBCST(d.getIcms().getModBCST().getValor());
                _30.setPMVAST(d.getIcms().getPMVAST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPMVAST()) : null);
                _30.setPRedBCST(d.getIcms().getPRedBCST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCST()) : null);
                _30.setVBCST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCST()));
                _30.setPICMSST(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMSST()));
                _30.setVICMSST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMSST()));
                icms.setICMS30(_30);
                break;
            case ICMS_40:
                ICMS40 _40 = new ICMS40();
                _40.setOrig(d.getIcms().getOrigem().getValor());
                _40.setCST(d.getIcms().getCstICMS().getValor());
                _40.setVICMSDeson(d.getIcms().getVICMSDeson().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMSDeson()) : null);
                _40.setMotDesICMS(d.getIcms().getMotDesICMS().getValor());
                icms.setICMS40(_40);
                break;
            case ICMS_41:
                ICMS40 _41 = new ICMS40();
                _41.setOrig(d.getIcms().getOrigem().getValor());
                _41.setCST(d.getIcms().getCstICMS().getValor());
                _41.setVICMSDeson(d.getIcms().getVICMSDeson().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMSDeson()) : null);
                _41.setMotDesICMS(d.getIcms().getMotDesICMS().getValor());
                icms.setICMS40(_41);
                break;
            case ICMS_50:
                ICMS40 _50 = new ICMS40();
                _50.setOrig(d.getIcms().getOrigem().getValor());
                _50.setCST(d.getIcms().getCstICMS().getValor());
                _50.setVICMSDeson(d.getIcms().getVICMSDeson().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getVICMSDeson()) : null);
                _50.setMotDesICMS(d.getIcms().getMotDesICMS().getValor());
                icms.setICMS40(_50);
                break;
            case ICMS_51:
                ICMS51 _51 = new ICMS51();
                _51.setOrig(d.getIcms().getOrigem().getValor());
                _51.setCST(d.getIcms().getCstICMS().getValor());
                _51.setPRedBC(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCICMS()));
                _51.setVBC(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCICMS()));
                _51.setPICMS(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMS()));
                _51.setVICMSOp(null);
                _51.setPDif(null);
                _51.setVICMSDif(null);
                _51.setVICMS(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMS()));
                icms.setICMS51(_51);
                break;
            case ICMS_60:
                ICMS60 _60 = new ICMS60();
                _60.setOrig(d.getIcms().getOrigem().getValor());
                _60.setCST(d.getIcms().getCstICMS().getValor());
                _60.setVBCSTRet(null);
                _60.setVICMSSTRet(null);
                icms.setICMS60(_60);
                break;
            case ICMS_70:
                ICMS70 _70 = new ICMS70();
                _70.setOrig(d.getIcms().getOrigem().getValor());
                _70.setCST(d.getIcms().getCstICMS().getValor());
                _70.setModBC(d.getIcms().getModBCICMS().getValor());
                _70.setPRedBC(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCICMS()));
                _70.setVBC(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCICMS()));
                _70.setPICMS(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMS()));
                _70.setVICMS(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMS()));
                _70.setModBCST(d.getIcms().getModBCST().getValor());
                _70.setPMVAST(d.getIcms().getPMVAST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPMVAST()) : null);
                _70.setPRedBCST(d.getIcms().getPRedBCST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCST()) : null);
                _70.setVBCST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCST()));
                _70.setPICMSST(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMSST()));
                _70.setVICMSST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMSST()));
                _70.setVICMSDeson(d.getIcms().getVICMSDeson().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getVICMSDeson()) : null);
                _70.setMotDesICMS(d.getIcms().getMotDesICMS().getValor());
                icms.setICMS70(_70);
                break;
            case ICMS_90:
                ICMS90 _90 = new ICMS90();
                _90.setOrig(d.getIcms().getOrigem().getValor());
                _90.setCST(d.getIcms().getCstICMS().getValor());
                _90.setModBC(d.getIcms().getModBCICMS().getValor());
                _90.setPRedBC(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCICMS()));
                _90.setVBC(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCICMS()));
                _90.setPICMS(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMS()));
                _90.setVICMS(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMS()));
                _90.setModBCST(d.getIcms().getModBCST().getValor());
                _90.setPMVAST(d.getIcms().getPMVAST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPMVAST()) : null);
                _90.setPRedBCST(d.getIcms().getPRedBCST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCST()) : null);
                _90.setVBCST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCST()));
                _90.setPICMSST(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMSST()));
                _90.setVICMSST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMSST()));
                _90.setVICMSDeson(d.getIcms().getVICMSDeson().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getVICMSDeson()) : null);
                _90.setMotDesICMS(d.getIcms().getMotDesICMS().getValor());
                icms.setICMS90(_90);
                break;
            case ICMS_Part10:
                ICMSPart p10 = new ICMSPart();
                p10.setOrig(d.getIcms().getOrigem().getValor());
                p10.setCST(d.getIcms().getCstICMS().getValor());
                p10.setModBC(d.getIcms().getModBCICMS().getValor());
                p10.setPRedBC(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCICMS()));
                p10.setVBC(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCICMS()));
                p10.setPICMS(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMS()));
                p10.setVICMS(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMS()));
                p10.setModBCST(d.getIcms().getModBCST().getValor());
                p10.setPMVAST(d.getIcms().getPMVAST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPMVAST()) : null);
                p10.setPRedBCST(d.getIcms().getPRedBCST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCST()) : null);
                p10.setVBCST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCST()));
                p10.setPICMSST(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMSST()));
                p10.setVICMSST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMSST()));
                p10.setPBCOp(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPBCOp()));
                p10.setUFST(TUf.valueOf(d.getIcms().getUFST().toString()));
                icms.setICMSPart(p10);
                break;
            case ICMS_Part90:
                ICMSPart p90 = new ICMSPart();
                p90.setOrig(d.getIcms().getOrigem().getValor());
                p90.setCST(d.getIcms().getCstICMS().getValor());
                p90.setModBC(d.getIcms().getModBCICMS().getValor());
                p90.setPRedBC(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCICMS()));
                p90.setVBC(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCICMS()));
                p90.setPICMS(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMS()));
                p90.setVICMS(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMS()));
                p90.setModBCST(d.getIcms().getModBCST().getValor());
                p90.setPMVAST(d.getIcms().getPMVAST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPMVAST()) : null);
                p90.setPRedBCST(d.getIcms().getPRedBCST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCST()) : null);
                p90.setVBCST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCST()));
                p90.setPICMSST(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMSST()));
                p90.setVICMSST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMSST()));
                p90.setPBCOp(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPBCOp()));
                p90.setUFST(TUf.valueOf(d.getIcms().getUFST().toString()));
                icms.setICMSPart(p90);
                break;
            case SN_101:
                ICMSSN101 _101 = new ICMSSN101();
                _101.setOrig(d.getIcms().getOrigem().getValor());
                _101.setCSOSN(d.getIcms().getCstICMS().getValor());
                _101.setPCredSN(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPCredSN()));
                _101.setVCredICMSSN(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVCredICMSSN()));
                icms.setICMSSN101(_101);
                break;
            case SN_102:
                ICMSSN102 _102 = new ICMSSN102();
                _102.setOrig(d.getIcms().getOrigem().getValor());
                _102.setCSOSN(d.getIcms().getCstICMS().getValor());
                icms.setICMSSN102(_102);
                break;
            case SN_103:
                ICMSSN102 _103 = new ICMSSN102();
                _103.setOrig(d.getIcms().getOrigem().getValor());
                _103.setCSOSN(d.getIcms().getCstICMS().getValor());
                icms.setICMSSN102(_103);
                break;
            case SN_300:
                ICMSSN102 _300 = new ICMSSN102();
                _300.setOrig(d.getIcms().getOrigem().getValor());
                _300.setCSOSN(d.getIcms().getCstICMS().getValor());
                icms.setICMSSN102(_300);
                break;
            case SN_400:
                ICMSSN102 _400 = new ICMSSN102();
                _400.setOrig(d.getIcms().getOrigem().getValor());
                _400.setCSOSN(d.getIcms().getCstICMS().getValor());
                icms.setICMSSN102(_400);
                break;
            case SN_201:
                ICMSSN201 _201 = new ICMSSN201();
                _201.setOrig(d.getIcms().getOrigem().getValor());
                _201.setCSOSN(d.getIcms().getCstICMS().getValor());
                _201.setModBCST(d.getIcms().getModBCST().getValor());
                _201.setPMVAST(d.getIcms().getPMVAST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPMVAST()) : null);
                _201.setPRedBCST(d.getIcms().getPRedBCST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCST()) : null);
                _201.setVBCST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCST()));
                _201.setPICMSST(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMSST()));
                _201.setVICMSST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMSST()));
                _201.setPCredSN(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPCredSN()));
                _201.setVCredICMSSN(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVCredICMSSN()));
                icms.setICMSSN201(_201);
                break;
            case SN_202:
                ICMSSN202 _202 = new ICMSSN202();
                _202.setOrig(d.getIcms().getOrigem().getValor());
                _202.setCSOSN(d.getIcms().getCstICMS().getValor());
                _202.setModBCST(d.getIcms().getModBCST().getValor());
                
                if(d.getIcms().getPMVAST() == null) {
                	_202.setPMVAST(null);
                } else {
                	_202.setPMVAST(d.getIcms().getPMVAST().compareTo(BigDecimal.ZERO) == 1
                            ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPMVAST()) : null);
                }
                
                if(d.getIcms().getPRedBCST() == null) {
                	_202.setPMVAST(null);
                } else {
                	_202.setPRedBCST(d.getIcms().getPRedBCST().compareTo(BigDecimal.ZERO) == 1
                            ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCST()) : null);
                }
                
                _202.setVBCST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCST()));
                _202.setPICMSST(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMSST()));
                _202.setVICMSST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMSST()));
                icms.setICMSSN202(_202);
                break;
            case SN_203:
                ICMSSN202 _203 = new ICMSSN202();
                _203.setOrig(d.getIcms().getOrigem().getValor());
                _203.setCSOSN(d.getIcms().getCstICMS().getValor());
                _203.setModBCST(d.getIcms().getModBCST().getValor());
                _203.setPMVAST(d.getIcms().getPMVAST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPMVAST()) : null);
                _203.setPRedBCST(d.getIcms().getPRedBCST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCST()) : null);
                _203.setVBCST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCST()));
                _203.setPICMSST(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMSST()));
                _203.setVICMSST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMSST()));
                icms.setICMSSN202(_203);
                break;
            case SN_500:
                ICMSSN500 _500 = new ICMSSN500();
                _500.setOrig(d.getIcms().getOrigem().getValor());
                _500.setCSOSN(d.getIcms().getCstICMS().getValor());
                icms.setICMSSN500(_500);
                break;
            case SN_900:
                ICMSSN900 _900 = new ICMSSN900();
                _900.setOrig(d.getIcms().getOrigem().getValor());
                _900.setCSOSN(d.getIcms().getCstICMS().getValor());
                if (d.getIcms().getModBCICMS() != null) {
                    _900.setModBC(d.getIcms().getModBCICMS().getValor());
                }
                _900.setPRedBC(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCICMS()));
                _900.setVBC(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCICMS()));
                _900.setPICMS(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMS()));
                _900.setVICMS(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMS()));
                if (d.getIcms().getModBCST() != null) {
                    _900.setModBCST(d.getIcms().getModBCST().getValor());
                }
                _900.setPMVAST(d.getIcms().getPMVAST().compareTo(BigDecimal.ZERO) == 1
                        ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPMVAST()) : null);
                if (d.getIcms().getPRedBCST() != null) {
                    _900.setPRedBCST(d.getIcms().getPRedBCST().compareTo(BigDecimal.ZERO) == 1
                            ? ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPRedBCST()) : null);
                }
                _900.setVBCST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVBCST()));
                _900.setPICMSST(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPICMSST()));
                _900.setVICMSST(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVICMSST()));
                _900.setPCredSN(ConversorBigDecimal.paraStringNFeAliq(d.getIcms().getPCredSN()));
                _900.setVCredICMSSN(ConversorBigDecimal.paraStringNFeValor(d.getIcms().getVCredICMSSN()));
                icms.setICMSSN900(_900);
                break;
            default:
                break;
        }
        return icms;
    }

    private static TIpi getIPI(ItemNotaFiscal i) {
        IPI ip = i.getDetFiscal().getIpi();
        TIpi ipi = new TIpi();
        ipi.setCNPJProd(ip.getCNPJProd());
        ipi.setCSelo(ip.getCSelo());
        ipi.setQSelo(ConversorBigDecimal.paraStringNFeQuant(ip.getQSelo()));
        if (ipi.getQSelo().equals("0")) {
            ipi.setQSelo(null);
        }
        ipi.setCEnq(ip.getCEnq());
        if(ip.getCEnq()==null){
            ipi.setCEnq("999");
        }
        CST_IPI CST_IPI = ip.getCstIPI();

        IPITrib ipiTrib = new IPITrib();
        TpCalcIPI tpCalc = ip.getTpCalcIPI();

        IPINT ipiNT = new IPINT();

        switch (CST_IPI) {
            case IPI_00:
                ipiTrib.setCST(ip.getCstIPI().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        ipiTrib.setVBC(ConversorBigDecimal.paraStringNFeValor(ip.getVBCIPI()));
                        ipiTrib.setPIPI(ConversorBigDecimal.paraStringNFeAliq(ip.getPIPI()));
                        break;
                    case UNIDADE:
                        ipiTrib.setQUnid(ConversorBigDecimal.paraStringNFeQuant(ip.getQUnid()));
                        ipiTrib.setVUnid(ConversorBigDecimal.paraStringNFePreco(ip.getVUnid()));
                        break;
                    default:
                        break;
                }
                ipiTrib.setVIPI(ConversorBigDecimal.paraStringNFeValor(ip.getVIPI()));
                ipi.setIPITrib(ipiTrib);
                break;
            case IPI_49:
                ipiTrib.setCST(ip.getCstIPI().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        ipiTrib.setVBC(ConversorBigDecimal.paraStringNFeValor(ip.getVBCIPI()));
                        ipiTrib.setPIPI(ConversorBigDecimal.paraStringNFeAliq(ip.getPIPI()));
                        break;
                    case UNIDADE:
                        ipiTrib.setQUnid(ConversorBigDecimal.paraStringNFeQuant(ip.getQUnid()));
                        ipiTrib.setVUnid(ConversorBigDecimal.paraStringNFePreco(ip.getVUnid()));
                        break;
                    default:
                        break;
                }
                ipiTrib.setVIPI(ConversorBigDecimal.paraStringNFeValor(ip.getVIPI()));
                ipi.setIPITrib(ipiTrib);
                break;
            case IPI_50:
                ipiTrib.setCST(ip.getCstIPI().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        ipiTrib.setVBC(ConversorBigDecimal.paraStringNFeValor(ip.getVBCIPI()));
                        ipiTrib.setPIPI(ConversorBigDecimal.paraStringNFeAliq(ip.getPIPI()));
                        break;
                    case UNIDADE:
                        ipiTrib.setQUnid(ConversorBigDecimal.paraStringNFeQuant(ip.getQUnid()));
                        ipiTrib.setVUnid(ConversorBigDecimal.paraStringNFePreco(ip.getVUnid()));
                        break;
                    default:
                        break;
                }
                ipiTrib.setVIPI(ConversorBigDecimal.paraStringNFeValor(ip.getVIPI()));
                ipi.setIPITrib(ipiTrib);
                break;
            case IPI_99:
                ipiTrib.setCST(ip.getCstIPI().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        ipiTrib.setVBC(ConversorBigDecimal.paraStringNFeValor(ip.getVBCIPI()));
                        ipiTrib.setPIPI(ConversorBigDecimal.paraStringNFeAliq(ip.getPIPI()));
                        break;
                    case UNIDADE:
                        ipiTrib.setQUnid(ConversorBigDecimal.paraStringNFeQuant(ip.getQUnid()));
                        ipiTrib.setVUnid(ConversorBigDecimal.paraStringNFePreco(ip.getVUnid()));
                        break;
                    default:
                        break;
                }
                ipiTrib.setVIPI(ConversorBigDecimal.paraStringNFeValor(ip.getVIPI()));
                ipi.setIPITrib(ipiTrib);
                break;
            case IPI_01:
                ipiNT.setCST(ip.getCstIPI().getValor());
                ipi.setIPINT(ipiNT);
                break;
            case IPI_02:
                ipiNT.setCST(ip.getCstIPI().getValor());
                ipi.setIPINT(ipiNT);
                break;
            case IPI_03:
                ipiNT.setCST(ip.getCstIPI().getValor());
                ipi.setIPINT(ipiNT);
                break;
            case IPI_04:
                ipiNT.setCST(ip.getCstIPI().getValor());
                ipi.setIPINT(ipiNT);
                break;
            case IPI_05:
                ipiNT.setCST(ip.getCstIPI().getValor());
                ipi.setIPINT(ipiNT);
                break;
            case IPI_51:
                ipiNT.setCST(ip.getCstIPI().getValor());
                ipi.setIPINT(ipiNT);
                break;
            case IPI_52:
                ipiNT.setCST(ip.getCstIPI().getValor());
                ipi.setIPINT(ipiNT);
                break;
            case IPI_53:
                ipiNT.setCST(ip.getCstIPI().getValor());
                ipi.setIPINT(ipiNT);
                break;
            case IPI_54:
                ipiNT.setCST(ip.getCstIPI().getValor());
                ipi.setIPINT(ipiNT);
                break;
            case IPI_55:
                ipiNT.setCST(ip.getCstIPI().getValor());
                ipi.setIPINT(ipiNT);
                break;

            default:
                break;
        }

        return ipi;
    }

    private static PIS getPIS(ItemNotaFiscal i) {
        PIS pis = new PIS();
        br.com.eiasiscon.produto.tributacao.pis.PIS p = i.getDetFiscal().getPis();
        CST_PIS cst = p.getCstPIS();
        PISAliq pA = new PISAliq();
        PISQtde pQ = new PISQtde();
        PISNT pN = new PISNT();
        PISOutr pO = new PISOutr();

        TpCalcPIS tpCalc = p.getTpCalcPis();

        switch (cst) {
            case PIS_01:
                pA.setCST(p.getCstPIS().getValor());
                pA.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                pA.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                pA.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISAliq(pA);
                break;
            case PIS_02:
                pA.setCST(p.getCstPIS().getValor());
                pA.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                pA.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                pA.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISAliq(pA);
                break;
            case PIS_03:
                pQ.setCST(p.getCstPIS().getValor());
                pQ.setQBCProd(ConversorBigDecimal.paraStringNFeQuant(p.getQBCProdPIS()));
                pQ.setVAliqProd(ConversorBigDecimal.paraStringNFePreco(p.getVAliqProdPIS()));
                pQ.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISQtde(pQ);
                break;
            case PIS_04:
                pN.setCST(p.getCstPIS().getValor());
                pis.setPISNT(pN);
                break;
            case PIS_05:
                pN.setCST(p.getCstPIS().getValor());
                pis.setPISNT(pN);
                break;
            case PIS_06:
                pN.setCST(p.getCstPIS().getValor());
                pis.setPISNT(pN);
                break;
            case PIS_07:
                pN.setCST(p.getCstPIS().getValor());
                pis.setPISNT(pN);
                break;
            case PIS_08:
                pN.setCST(p.getCstPIS().getValor());
                pis.setPISNT(pN);
                break;
            case PIS_09:
                pN.setCST(p.getCstPIS().getValor());
                pis.setPISNT(pN);
                break;
            case PIS_49:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;

                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_50:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;

                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_51:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;

                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_52:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;

                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_53:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;

                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_54:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;

                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_55:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;

                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_56:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;

                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_60:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;

                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_61:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;

                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_62:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;

                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_63:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;

                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_64:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;

                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_65:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;
                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_66:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;
                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_67:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;
                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_70:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;
                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_71:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;
                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_72:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;
                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_73:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;
                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_74:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;
                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_75:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;
                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_98:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;
                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;
            case PIS_99:
                pO.setCST(p.getCstPIS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPIS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPIS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPIS()));
                        break;
                    default:
                        break;
                }
                pO.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPIS()));
                pis.setPISOutr(pO);
                break;

            default:
                break;
        }

        return pis;
    }

    private static PISST getPISST(ItemNotaFiscal i) {
        br.com.eiasiscon.produto.tributacao.pis.PISST p = i.getDetFiscal().getPisST();
        if (p == null) {
            p = br.com.eiasiscon.produto.tributacao.pis.PISST.builder().build();
        }
        PISST pisST = new PISST();
        TpCalcPIS tpCalc = p.getTpCalcPISST();
        if (tpCalc == null) {
            tpCalc = TpCalcPIS.NA;
        }
        switch (tpCalc) {
            case ALIQUOTA:
                pisST.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCPISST()));
                pisST.setPPIS(ConversorBigDecimal.paraStringNFeAliq(p.getPPISST()));
                pisST.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPISST()));
                break;
            case UNIDADE:
                pisST.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCPISST()));
                pisST.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdPISST()));
                pisST.setVPIS(ConversorBigDecimal.paraStringNFeValor(p.getVPISST()));
                break;

            default:
                return null;
        }

        return pisST;
    }

    private static COFINS getCOFINS(ItemNotaFiscal i) {
        COFINS cofins = new COFINS();
        br.com.eiasiscon.produto.tributacao.cofins.COFINS p = i.getDetFiscal().getCofins();
        CST_COFINS cst = p.getCstCOFINS();
        COFINSAliq pA = new COFINSAliq();
        COFINSQtde pQ = new COFINSQtde();
        COFINSNT pN = new COFINSNT();
        COFINSOutr pO = new COFINSOutr();

        TpCalcCOFINS tpCalc = p.getTpCalcCofins();

        switch (cst) {
            case COFINS_01:
                pA.setCST(p.getCstCOFINS().getValor());
                pA.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                pA.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                pA.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSAliq(pA);
                break;
            case COFINS_02:
                pA.setCST(p.getCstCOFINS().getValor());
                pA.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                pA.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                pA.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSAliq(pA);
                break;
            case COFINS_03:
                pQ.setCST(p.getCstCOFINS().getValor());
                pQ.setQBCProd(ConversorBigDecimal.paraStringNFeQuant(p.getQBCProdCOFINS()));
                pQ.setVAliqProd(ConversorBigDecimal.paraStringNFePreco(p.getVAliqProdCOFINS()));
                pQ.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSQtde(pQ);
                break;
            case COFINS_04:
                pN.setCST(p.getCstCOFINS().getValor());
                cofins.setCOFINSNT(pN);
                break;
            case COFINS_05:
                pN.setCST(p.getCstCOFINS().getValor());
                cofins.setCOFINSNT(pN);
                break;
            case COFINS_06:
                pN.setCST(p.getCstCOFINS().getValor());
                cofins.setCOFINSNT(pN);
                break;
            case COFINS_07:
                pN.setCST(p.getCstCOFINS().getValor());
                cofins.setCOFINSNT(pN);
                break;
            case COFINS_08:
                pN.setCST(p.getCstCOFINS().getValor());
                cofins.setCOFINSNT(pN);
                break;
            case COFINS_09:
                pN.setCST(p.getCstCOFINS().getValor());
                cofins.setCOFINSNT(pN);
                break;
            case COFINS_49:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;

                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_50:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;

                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_51:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;

                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_52:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;

                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_53:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;

                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_54:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;

                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_55:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;

                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_56:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;

                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_60:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;

                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_61:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;

                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_62:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;

                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_63:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;

                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_64:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;

                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_65:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;
                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_66:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;
                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_67:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;
                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_70:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;
                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_71:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;
                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_72:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;
                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_73:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;
                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_74:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;
                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_75:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;
                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_98:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;
                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;
            case COFINS_99:
                pO.setCST(p.getCstCOFINS().getValor());
                switch (tpCalc) {
                    case ALIQUOTA:
                        pO.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINS()));
                        break;
                    case UNIDADE:
                        pO.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINS()));
                        pO.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINS()));
                        break;
                    default:
                        break;
                }
                pO.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINS()));
                cofins.setCOFINSOutr(pO);
                break;

            default:
                break;
        }

        return cofins;
    }

    private static COFINSST getcofinsST(ItemNotaFiscal i) {
    	br.com.eiasiscon.produto.tributacao.cofins.COFINSST p = i.getDetFiscal().getCofinsST();
        if (p == null) {
            p = br.com.eiasiscon.produto.tributacao.cofins.COFINSST.builder().build();
        }
        COFINSST cofinsSTST = new COFINSST();
        TpCalcCOFINS tpCalc = p.getTpCalcCOFINSST();
        if (tpCalc == null) {
            tpCalc = TpCalcCOFINS.NA;
        }
        switch (tpCalc) {
            case ALIQUOTA:
                cofinsSTST.setVBC(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINSST()));
                cofinsSTST.setPCOFINS(ConversorBigDecimal.paraStringNFeAliq(p.getPCOFINSST()));
                cofinsSTST.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINSST()));
                break;
            case UNIDADE:
                cofinsSTST.setQBCProd(ConversorBigDecimal.paraStringNFeValor(p.getVBCCOFINSST()));
                cofinsSTST.setVAliqProd(ConversorBigDecimal.paraStringNFeQuant(p.getVAliqProdCOFINSST()));
                cofinsSTST.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(p.getVCOFINSST()));
                break;

            default:
                return null;
        }
        return cofinsSTST;
    }

    private static Total totaisDaNFe(NotaFiscal nf) {
        br.com.eiasiscon.notafiscal.total.Total t = nf.getTotal();
        Total total = new Total();

        ICMSTot icmstot = new ICMSTot();
        icmstot.setVBC(ConversorBigDecimal.paraStringNFeValor(t.getVBC()));
        icmstot.setVICMS(ConversorBigDecimal.paraStringNFeValor(t.getVICMS()));
        icmstot.setVBCST(ConversorBigDecimal.paraStringNFeValor(t.getVBCST()));
        icmstot.setVST(ConversorBigDecimal.paraStringNFeValor(t.getVST()));
        icmstot.setVProd(ConversorBigDecimal.paraStringNFeValor(t.getVProd()));
        icmstot.setVFrete(ConversorBigDecimal.paraStringNFeValor(t.getVFrete()));
        icmstot.setVSeg(ConversorBigDecimal.paraStringNFeValor(t.getVSeg()));
        icmstot.setVDesc(ConversorBigDecimal.paraStringNFeValor(t.getVDesc()));
        icmstot.setVII(ConversorBigDecimal.paraStringNFeValor(t.getVII()));
        icmstot.setVIPI(ConversorBigDecimal.paraStringNFeValor(t.getVIPI()));
        icmstot.setVPIS(ConversorBigDecimal.paraStringNFeValor(t.getVPIS()));
        icmstot.setVCOFINS(ConversorBigDecimal.paraStringNFeValor(t.getVCOFINS()));
        icmstot.setVOutro(ConversorBigDecimal.paraStringNFeValor(t.getVOutro()));
        icmstot.setVNF(ConversorBigDecimal.paraStringNFeValor(t.getVNF()));
        icmstot.setVICMSDeson(ConversorBigDecimal.paraStringNFeValor(t.getVICMSDeson()));
        icmstot.setVFCP(ConversorBigDecimal.paraStringNFeValor(t.getVFCP()));
        icmstot.setVFCPST(ConversorBigDecimal.paraStringNFeValor(t.getVFCPST()));
        icmstot.setVFCPSTRet(ConversorBigDecimal.paraStringNFeValor(t.getVFCPSTRet()));
        icmstot.setVIPIDevol(ConversorBigDecimal.paraStringNFeValor(t.getVIPIDevol()));
        total.setICMSTot(icmstot);

        return total;
    }

    private static Transp dadosDoTransporte(NotaFiscal nf) {
        Transporte t = nf.getTransp();
        Transp transp = new Transp();
        if (t.getModFrete() == null) {
            t.setModFrete(ModFrete.SEM_FRETE);
        }
        transp.setModFrete(t.getModFrete().getValor());

        /**
         * Dados da Transportadora.
         */
        
        Transporta transporta = new Transporta();
        if (t.getTransporta() != null) {
            if(t.getTransporta().getNumDoc()!=null ){
                transporta.setCNPJ(t.getTransporta().getNumDoc().replaceAll("[^0-9]", ""));
            }
            transporta.setXNome(t.getTransporta().getNome());
            if (t.getTransporta().getIE() != null) {
                transporta.setIE(t.getTransporta().getIE()
                        .replaceAll("[^0-9]", ""));
            }
            if(t.getTransporta().getIE()==null){
            	transporta.setIE(null);
            }
            if(t.getTransporta().getLogradouro()==null){
                transporta.setXEnder(null);
            }else{
                transporta.setXEnder(t.getTransporta().getLogradouro());
            }
            
            transporta.setXMun(t.getTransporta().getMunicipio().getXMun());
            
            if (t.getTransporta().getMunicipio().getUf() != null ) {
            	transporta.setUF(TUf.valueOf(t.getTransporta().getMunicipio().getUf().toString()));
            }
            
            if(transporta.getXNome() != null) {
            	transp.setTransporta(transporta);
            }
			
		}


        /**
         * Dados do Veiculo.
         */
        TVeiculo veicTransp = new TVeiculo();
        if (t.getVeicTransp().getUf() != null) {
            veicTransp.setPlaca(t.getVeicTransp().getPlaca());
            veicTransp.setUF(TUf.valueOf(t.getVeicTransp().getUf().toString()));
            veicTransp.setRNTC(t.getVeicTransp().getRntc());
            if(t.getVeicTransp().getRntc() != null){
            	if(t.getVeicTransp().getRntc().isEmpty()){
                	veicTransp.setRNTC(null);
                }
            }
            transp.setVeicTransp(veicTransp);
        }
        if (t.getReboque() != null) {
            for (Veiculo v : t.getReboque()) {
                TVeiculo reboque = new TVeiculo();
                reboque.setPlaca(v.getPlaca());
                reboque.setUF(TUf.valueOf(v.getUf().toString()));
                reboque.setRNTC(v.getRntc());
                if(v.getRntc().isEmpty()){
                	reboque.setRNTC(null);
                }
                transp.getReboque().add(reboque);
            }
            if (t.getModFrete().equals(ModFrete.SEM_FRETE)) {
    			transp.setTransporta(null);
    			transp.setVeicTransp(null);
    		}
        }

        /**
         * Dados de Volumes.
         */
        Vol vol = new Vol();
        vol.setQVol(null);
        vol.setNVol(null);
        if(t.getVol()!=null) {
        	vol.setPesoL(ConversorBigDecimal.paraStringNFePeso(t.getVol().get(0).getPesoL()));
            vol.setPesoB(ConversorBigDecimal.paraStringNFePeso(t.getVol().get(0).getPesoB()));
        }        
        transp.getVol().add(vol);

        return transp;
    }

    private static InfAdic informacoesAdicionais(NotaFiscal nf) {
        InfAdic infAdic = new InfAdic();
        
        if(nf.getInfAdic()!=null){
        if (nf.getInfAdic().getInfAdFisco() != null) {
            infAdic.setInfAdFisco(nf.getInfAdic().getInfAdFisco().trim().replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", ""));
            if (infAdic.getInfAdFisco().isEmpty()) {
                infAdic.setInfAdFisco(null);
            }
        }
        if (nf.getInfAdic().getInfCpl() != null) {
            infAdic.setInfCpl(nf.getInfAdic().getInfCpl());
            if (infAdic.getInfCpl().isEmpty()) {
                infAdic.setInfCpl(null);
            }
        }
        }
        if (infAdic.getInfAdFisco() == null && infAdic.getInfCpl() == null) {
            return null;
        }
        return infAdic;
    }

    private static Pag getPag(NotaFiscal nf) {
    	Pag pag = new Pag();    	
    	for(DetPag d : nf.getPag().getDetPag()) {
    		Pag.DetPag detPag = new Pag.DetPag();
    		if(d.getIndPag() != null) {
    			detPag.setIndPag(d.getIndPag().getValor());
    		}
            detPag.setTPag(d.getTPag().getValor());
            detPag.setVPag(ConversorBigDecimal.paraStringNFeValor(d.getVPag()));
            
            pag.getDetPag().add(detPag);
    	}
        return pag;
    }
    
    private static Cobr getCobr(NotaFiscal nf) {
        Cobranca c = nf.getCobr();
        Cobr cobr = new Cobr();

        Fat fat = new Fat();
        if(c != null) {
        	fat.setNFat(c.getFat().getNFat());
            fat.setVOrig(ConversorBigDecimal.paraStringNFeValor(c.getFat().getVOrig()));
            fat.setVDesc(ConversorBigDecimal.paraStringNFeValor(c.getFat().getVDesc()));
            fat.setVLiq(ConversorBigDecimal.paraStringNFeValor(c.getFat().getVLiq()));
            if (!fat.getNFat().isEmpty()) {
                cobr.setFat(fat);
            }
            
            if (c.getDup() != null) {
                for (Duplicata d : c.getDup()) {
                    Dup dup = new Dup();
                    dup.setNDup(d.getNumero());
                    dup.setDVenc(ConversorDate.retornaDataNFe(d.getVencimento()));
                    dup.setVDup(ConversorBigDecimal.paraStringNFeValor(d.getValor()));
                    cobr.getDup().add(dup);
                }
            }
        }

        return cobr;
    }

}