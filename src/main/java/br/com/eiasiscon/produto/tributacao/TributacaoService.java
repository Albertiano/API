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
public class TributacaoService extends BaseService<Tributacao, Long> {
	
	@Autowired
	private TributacaoRepository repository;
	
	@Autowired
	public void setJpaRepository(TributacaoRepository jpa) {
		setJpa(jpa);
	}
	
	public Page<Tributacao> find(String query, Long empresa, Pageable pageable) {
		Page<Tributacao>  entities = repository.find(query, empresa, pageable);
		return entities;
	}
	
	public Tributacao instance() {
		return Tributacao.builder().nome("Padrão").descricao("Descrição Padrão").destinos(createDestinos()).build();
	}
	
	private List<Destino> createDestinos() {
		List<Destino> destinos = new ArrayList<>();
		for (UF uf : UF.values()) {
			String cfop = null;

			if (uf.equals(UF.PB)) {
				cfop = "5102";
			} else {
				cfop = "6102";
			}
			destinos.add(Destino.builder().estado(uf).cfop(cfop).icms(createICMS()).ipi(createIPI()).pis(createPIS())
					.cofins(createCOFINS()).build());
		}
		return destinos;
	}

    private ICMS createICMS() {
    	return ICMS.builder()
        .cstICMS(CST_ICMS.SN_102)
        .origem(Origem.NACIONAL)
        .modBCICMS(ModBC.OPERACAO)
        .modBCST(ModBCST.PAUTA)
        .vBCICMS(BigDecimal.ZERO)     
        .pICMS(BigDecimal.ZERO)
        .vICMS(BigDecimal.ZERO)
        .pMVAST(BigDecimal.ZERO)
        .vICMSST(BigDecimal.ZERO)
        .build();
        
    }
    
    private IPI createIPI() {
    	return IPI.builder()
        .cstIPI(CST_IPI.IPI_53)
        .cEnq("999")
        .build();
        
        
    }
    
    private PIS createPIS() {
    	return PIS.builder()
        .cstPIS(CST_PIS.PIS_07)
        .build();
    }
    
    private COFINS createCOFINS() {
    	return COFINS.builder()
        .cstCOFINS(CST_COFINS.COFINS_07)
        .build();
    }
}
