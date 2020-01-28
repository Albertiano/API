package br.com.eiasiscon.produto.tributacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eiasiscon.base.BaseService;
import br.com.eiasiscon.municipio.UF;
import br.com.eiasiscon.produto.tributacao.Destino;
import br.com.eiasiscon.produto.tributacao.Tributacao;
import br.com.eiasiscon.produto.tributacao.TributacaoRepository;
import br.com.eiasiscon.produto.tributacao.cofins.COFINS;
import br.com.eiasiscon.produto.tributacao.cofins.CST_COFINS;
import br.com.eiasiscon.produto.tributacao.icms.CST_ICMS;
import br.com.eiasiscon.produto.tributacao.icms.ICMS;
import br.com.eiasiscon.produto.tributacao.icms.ModBC;
import br.com.eiasiscon.produto.tributacao.icms.ModBCST;
import br.com.eiasiscon.produto.tributacao.icms.Origem;
import br.com.eiasiscon.produto.tributacao.ipi.CST_IPI;
import br.com.eiasiscon.produto.tributacao.ipi.IPI;
import br.com.eiasiscon.produto.tributacao.pis.CST_PIS;
import br.com.eiasiscon.produto.tributacao.pis.PIS;

@Service
public class TributacaoService extends BaseService<Tributacao, String> {
	
	@Autowired
	private TributacaoRepository repository;
	
	@Autowired
	public void setJpaRepository(TributacaoRepository jpa) {
		setJpa(jpa);
	}
	
	public Page<Tributacao> find(String query, String empresa, Pageable pageable) {
		Page<Tributacao>  entities = repository.find(query, empresa, pageable);
		return entities;
	}
	
	public Tributacao instance() {
		Tributacao entity = new Tributacao();
		entity.setNome("Padrão");
		entity.setDescricao("Descrição Padrão");
		if(entity.getDestinos() == null) {
			entity.setDestinos(createDestinos());
		}
		return entity;
	}
	
	private List<Destino> createDestinos() {
        List<Destino> destinos = new ArrayList<>();
        for (UF uf : UF.values()) {
            Destino d = new Destino();            
            d.setEstado(uf);
            if(uf.equals(UF.PB)) {
            	d.setCfop("5102");
            } else {
            	d.setCfop("6102");
            }
            d.setIcms(createICMS());
            d.setIpi(createIPI());
            d.setPis(createPIS());
            d.setCofins(createCOFINS());
            
            destinos.add(d);
        }
        return destinos;
    }

    private ICMS createICMS() {
        ICMS icms = new ICMS();
        icms.setCstICMS(CST_ICMS.SN_102);
        icms.setOrigem(Origem.NACIONAL);
        icms.setModBCICMS(ModBC.OPERACAO);
        icms.setModBCST(ModBCST.PAUTA);
        icms.setvBCICMS(BigDecimal.ZERO);        
        icms.setpICMS(BigDecimal.ZERO);
        icms.setvICMS(BigDecimal.ZERO);
        icms.setpMVAST(BigDecimal.ZERO);
        icms.setpICMSST(BigDecimal.ZERO);
        
        return icms;
    }
    
    private IPI createIPI() {
    	IPI ipi = new IPI();
        ipi.setCstIPI(CST_IPI.IPI_53);
        ipi.setcEnq("999");
        
        return ipi;
    }
    
    private PIS createPIS() {
    	PIS pis = new PIS();
        pis.setCstPIS(CST_PIS.PIS_07);
        
        return pis;
    }
    
    private COFINS createCOFINS() {
    	COFINS cofins = new COFINS();
        cofins.setCstCOFINS(CST_COFINS.COFINS_07);
        
        return cofins;
    }
}
