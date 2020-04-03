package br.com.eiasiscon.notafiscal;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.eiasiscon.base.BaseService;
import br.com.eiasiscon.municipio.UF;
import br.com.eiasiscon.nfe.NFeService;
import br.com.eiasiscon.nfe.common.NFeChaveAcesso;
import br.com.eiasiscon.notafiscal.item.DetalheFiscal;
import br.com.eiasiscon.notafiscal.item.ItemNotaFiscal;
import br.com.eiasiscon.pdv.config.ConfigPdv;
import br.com.eiasiscon.pdv.config.ConfigPdvService;
import br.com.eiasiscon.produto.Produto;
import br.com.eiasiscon.produto.ProdutoRepository;
import br.com.eiasiscon.produto.tributacao.Destino;
import br.com.eiasiscon.produto.tributacao.Tributacao;
import br.com.eiasiscon.produto.tributacao.cofins.COFINS;
import br.com.eiasiscon.produto.tributacao.cofins.COFINSST;
import br.com.eiasiscon.produto.tributacao.icms.ICMS;
import br.com.eiasiscon.produto.tributacao.icms.ModBC;
import br.com.eiasiscon.produto.tributacao.icms.ModBCST;
import br.com.eiasiscon.produto.tributacao.ipi.IPI;
import br.com.eiasiscon.produto.tributacao.ipi.TpCalcIPI;
import br.com.eiasiscon.produto.tributacao.pis.PIS;
import br.com.eiasiscon.produto.tributacao.pis.PISST;

@Service
public class NotaFiscalService extends BaseService<NotaFiscal, Long> {
	
	@Autowired
	private NotaFiscalRepository repository;
	@Autowired
	private ProdutoRepository repositoryProd;
	@Autowired
	private NFeService serviceNFe;
	@Autowired
	private ConfigPdvService pdvNFe;
	
	@Autowired
	public void setJpaRepository(NotaFiscalRepository jpa) {
		setJpa(jpa);
	}
	
	public Page<NotaFiscal> find(String query, Long empresa, Pageable pageable) {
		Page<NotaFiscal>  entities = repository.find(query, empresa, pageable);
		return entities;
	}
	
	public byte[] exportar(Long id) {
		try {
			NotaFiscal nf = repository.findById(id).get();

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
			ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

			if (nf.getSitNfe().equals("Autorizada")) {
				File fileToZip = new File(nf.getChave().concat("-procNFe.xml"));
				zipOutputStream.putNextEntry(new ZipEntry(fileToZip.getName()));
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(nf.getXml().getBytes());
				IOUtils.copy(byteArrayInputStream, zipOutputStream);
				
				File pdfToZip = new File(nf.getChave().concat(".pdf"));
				zipOutputStream.putNextEntry(new ZipEntry(pdfToZip.getName()));
				ByteArrayInputStream byteArrayInputStreamPdf = new ByteArrayInputStream(serviceNFe.GerarDanfe(nf.getId()));
				IOUtils.copy(byteArrayInputStreamPdf, zipOutputStream);
			}

			if (nf.getSitNfe().equals("Cancelada")) {
				File fileToZip = new File(nf.getChave().concat("-procNFe.xml"));
				zipOutputStream.putNextEntry(new ZipEntry(fileToZip.getName()));
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(nf.getXml().getBytes());
				IOUtils.copy(byteArrayInputStream, zipOutputStream);

				File fileToZipCancel = new File(nf.getChave().concat("-procEvento.xml"));
				zipOutputStream.putNextEntry(new ZipEntry(fileToZipCancel.getName()));
				ByteArrayInputStream byteArrayInputStreamCancel = new ByteArrayInputStream(
						nf.getProcEventoNFe().get(0).getXmlEvento().getBytes());
				IOUtils.copy(byteArrayInputStreamCancel, zipOutputStream);
			}

			zipOutputStream.closeEntry();

			if (zipOutputStream != null) {
				zipOutputStream.finish();
				zipOutputStream.flush();
				IOUtils.closeQuietly(zipOutputStream);
			}

			IOUtils.closeQuietly(bufferedOutputStream);
			IOUtils.closeQuietly(byteArrayOutputStream);
			try {
				Files.write(Paths.get(System.getProperty("user.home") + "/nota.zip"),
						byteArrayOutputStream.toByteArray());
			} catch (IOException e) {
				e.printStackTrace();
			}

			return byteArrayOutputStream.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public byte[] exportar(Long[] idNota) {
		try {
		
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);
        
        for(Long id : idNota) {
			NotaFiscal nf = repository.findById(id).get();
        	
        	if(nf.getSitNfe().equals("Autorizada")) {
        		File fileToZip = new File(nf.getChave().concat("-procNFe.xml"));
                zipOutputStream.putNextEntry(new ZipEntry(fileToZip.getName()));
                
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(nf.getXml().getBytes());

                IOUtils.copy(byteArrayInputStream, zipOutputStream);
        	}
        	
        	if(nf.getSitNfe().equals("Cancelada")) {
        		File fileToZip = new File(nf.getChave().concat("-procNFe.xml"));
                zipOutputStream.putNextEntry(new ZipEntry(fileToZip.getName()));
                
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(nf.getXml().getBytes());

                IOUtils.copy(byteArrayInputStream, zipOutputStream);
                
        		File fileToZipCancel = new File(nf.getChave().concat("-procEvento.xml"));
                zipOutputStream.putNextEntry(new ZipEntry(fileToZipCancel.getName()));
                
                ByteArrayInputStream byteArrayInputStreamCancel = new ByteArrayInputStream(nf.getProcEventoNFe().get(0).getXmlEvento().getBytes());

                IOUtils.copy(byteArrayInputStreamCancel, zipOutputStream);
        	}
                        
        }
        zipOutputStream.closeEntry();
        
        if (zipOutputStream != null) {
            zipOutputStream.finish();
            zipOutputStream.flush();
            IOUtils.closeQuietly(zipOutputStream);
        }
        
        IOUtils.closeQuietly(bufferedOutputStream);
        IOUtils.closeQuietly(byteArrayOutputStream);
        try {
			Files.write(Paths.get(System.getProperty("user.home") +"/notas.zip"), byteArrayOutputStream.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return byteArrayOutputStream.toByteArray();
        
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public byte[] gerarZip(Date ini, Date fim, Long empresa) {
		try {
		
		List<NotaFiscal> list = repository.findByEmissao(ini, fim, empresa);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);
        
        for (NotaFiscal nf : list) {
        	
        	if(nf.getSitNfe().equals("Autorizada")) {
        		File fileToZip = new File(nf.getChave().concat("-procNFe.xml"));
                zipOutputStream.putNextEntry(new ZipEntry(fileToZip.getName()));
                
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(nf.getXml().getBytes());

                IOUtils.copy(byteArrayInputStream, zipOutputStream);
        	}
        	
        	if(nf.getSitNfe().equals("Cancelada")) {
        		File fileToZip = new File(nf.getChave().concat("-procNFe.xml"));
                zipOutputStream.putNextEntry(new ZipEntry(fileToZip.getName()));
                
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(nf.getXml().getBytes());

                IOUtils.copy(byteArrayInputStream, zipOutputStream);
                
        		File fileToZipCancel = new File(nf.getChave().concat("-procEvento.xml"));
                zipOutputStream.putNextEntry(new ZipEntry(fileToZipCancel.getName()));
                
                ByteArrayInputStream byteArrayInputStreamCancel = new ByteArrayInputStream(nf.getProcEventoNFe().get(0).getXmlEvento().getBytes());

                IOUtils.copy(byteArrayInputStreamCancel, zipOutputStream);
        	}
                        
        }
        zipOutputStream.closeEntry();
        
        if (zipOutputStream != null) {
            zipOutputStream.finish();
            zipOutputStream.flush();
            IOUtils.closeQuietly(zipOutputStream);
        }
        
        IOUtils.closeQuietly(bufferedOutputStream);
        IOUtils.closeQuietly(byteArrayOutputStream);
        try {
			Files.write(Paths.get(System.getProperty("user.home") +"/notas.zip"), byteArrayOutputStream.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return byteArrayOutputStream.toByteArray();
        
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getSerieNFe(Long idPdv) {
		ConfigPdv pdv = pdvNFe.retrieve(idPdv);
		return pdv.getSerieNFe();
	}
	
	public int getSerieNFCe(Long idPdv) {
		ConfigPdv pdv = pdvNFe.retrieve(idPdv);
		return pdv.getSerieNFCe();
	}
	
	public int getNextNumeroNFe(Long idPdv) {
		ConfigPdv pdv = pdvNFe.retrieve(idPdv);
		pdv.setNumeroNFe(pdv.getNumeroNFe() + 1);
		pdvNFe.update(idPdv, pdv);
		return pdv.getNumeroNFe();
	}
	
	public int getNextNumeroNFCe(Long idPdv) {
		ConfigPdv pdv = pdvNFe.retrieve(idPdv);
		pdv.setNumeroNFCe(pdv.getNumeroNFCe() + 1);
		pdvNFe.update(idPdv, pdv);
		return pdv.getNumeroNFCe();
	}
	
	public NotaFiscal duplicar(Long id) {
		NotaFiscal entity = repository.findById(id).get();
		entity.setId(null);
		entity.setDhEmi(new Date());
		entity.setDhSaiEnt(new Date());
		entity.setSerie(getSerieNFe(entity.getPdv().getId()));
		entity.setNumero(getNextNumeroNFe(entity.getPdv().getId()));
		entity.setChave(NFeChaveAcesso.getChave(entity));
		entity.setSitNfe("Digitação");
		entity.setXml(null);
		entity.setProcEventoNFe(null);
		return repository.save(entity);
	}
	
	public ItemNotaFiscal getItem(Long idProd, BigDecimal quant, BigDecimal vUn, UF uf) {
		BigDecimal subtotal = quant.multiply(vUn).setScale(2, RoundingMode.HALF_UP);
		ItemNotaFiscal i = ItemNotaFiscal.builder().build();
		i.setProduto(repositoryProd.findById(idProd).get());
		i.setQuantidade(quant);
		i.setPrecoVenda(vUn);
		Produto p = i.getProduto();
		if (p.getPesoBruto() != null) {
			i.setPesoBruto(p.getPesoBruto().multiply(quant).setScale(2, RoundingMode.HALF_UP));
		}
		if (p.getPesoLiquido() != null) {
			i.setPesoLiquido(p.getPesoLiquido().multiply(quant).setScale(2, RoundingMode.HALF_UP));
		}
		i.setSubtotal(subtotal);
		i.setVFrete(BigDecimal.ZERO);
		i.setVDesc(BigDecimal.ZERO);
		i.setVSeg(BigDecimal.ZERO);
		i.setVOutro(BigDecimal.ZERO);
		i.setNoValorNota(false);
		
		Destino t = getDestino(p.getTributacao(), uf);	
		
		
		
		DetalheFiscal d  = new DetalheFiscal();
		d.setCfop(t.getCfop());
		d.setExtipi(p.getExtipi());
		d.setGenero(p.getGenero());
		d.setcEan(p.getCEan());
		d.setcEanTrib(p.getCEanTrib());
		if(p.getUtrib()!=null){
			d.setUtrib(p.getUtrib().getSigla());
		}		
		d.setqTrib(quant);
		d.setVuntrib(vUn);
		
		/**************   IPI  Inicio ****************************/
		IPI ipi = t.getIpi();
		if (ipi.getTpCalcIPI() == TpCalcIPI.ALIQUOTA) {
			ipi.setVBCIPI(i.getSubtotal());
			ipi.setVIPI(calculoPorcentagem(ipi.getVBCIPI(), ipi.getPIPI()));
		}
		
		if (ipi.getTpCalcIPI() == TpCalcIPI.UNIDADE) {
			ipi.setQUnid(quant.multiply(ipi.getQUnid()));;
			ipi.setVIPI(ipi.getQUnid().multiply(ipi.getVUnid()));
		}
		d.setIpi(ipi);
		/**************   IPI   Fim ****************************/
		
		/**************   ICMS Inicio **************************/
		ICMS icms = t.getIcms();
		
		if (icms.getModBCICMS() == ModBC.OPERACAO) {
			icms.setVBCICMS(i.getSubtotal());
			icms.setVICMS(calculoPorcentagem(icms.getVBCICMS(), icms.getPICMS()));
		}		
		if(ipi.isVIpiBcICMS()){
			icms.setVBCICMS(icms.getVBCICMS().add(ipi.getVIPI()));
			icms.setVICMS(calculoPorcentagem(icms.getVBCICMS(), icms.getPICMS()));
		}

		if (icms.getModBCST() == ModBCST.OPERACAO) {
			BigDecimal vAgreg = calculoPorcentagem(subtotal, icms.getPMVAST());

			icms.setVBCST(subtotal.add(vAgreg));
			icms.setVICMSST(calculoPorcentagem(icms.getVBCST(),
			icms.getPICMSST()).subtract(icms.getVICMS()));
		} else {
			icms.setVBCST(i.getQuantidade().multiply(icms.getVBCST()));
			icms.setVICMSST(calculoPorcentagem(icms.getVBCST(),
			icms.getPICMSST()).subtract(icms.getVICMS()));
		}
		d.setIcms(icms);
		/**************   ICMS Fim  **************************/
		
		PIS pis = t.getPis();
		d.setPis(pis);
		
		PISST pisST = t.getPisST();
		d.setPisST(pisST);
		
		COFINS cofins = t.getCofins();
		d.setCofins(cofins);
		
		COFINSST cofinsST = t.getCofinsST();
		d.setCofinsST(cofinsST);
		
		i.setDetFiscal(d);

		return i;
	}
	
	private BigDecimal calculoPorcentagem(BigDecimal valor, BigDecimal aliquota) {
		if(valor == null) {
			valor = BigDecimal.ZERO;
		}
		if(aliquota == null) {
			aliquota = BigDecimal.ZERO;
		}
		BigDecimal result = valor.multiply(aliquota).divide(
				new BigDecimal("100"), MathContext.DECIMAL128);
		return result;
	}
	
	private Destino getDestino(Tributacao trib, UF uf){
		for(Destino d: trib.getDestinos()){
		if(d.getEstado().equals(uf)){
			return d;
		}
	}	
	return null;
	}
}

