package br.com.eiasiscon.nfe;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eiasiscon.configuracao.Configuracao;
import br.com.eiasiscon.configuracao.ConfiguracaoService;
import br.com.eiasiscon.empresa.Empresa;
import br.com.eiasiscon.empresa.EmpresaRepository;
import br.com.eiasiscon.nfe.common.NFeConversor;
import br.com.eiasiscon.nfe.common.NFeDTO;
import br.com.eiasiscon.notafiscal.NotaFiscal;
import br.com.eiasiscon.notafiscal.NotaFiscalRepository;
import br.com.eiasiscon.uploadfiles.StorageService;
import br.com.swconsultoria.certificado.Certificado;
import br.com.swconsultoria.certificado.CertificadoService;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.Evento;
import br.com.swconsultoria.nfe.dom.enuns.AmbienteEnum;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import br.com.swconsultoria.nfe.dom.enuns.StatusEnum;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TEnvEvento;
import br.com.swconsultoria.nfe.schema.envEventoCancNFe.TRetEnvEvento;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TNfeProc;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TRetEnviNFe;
import br.com.swconsultoria.nfe.schema_4.retConsReciNFe.TRetConsReciNFe;
import br.com.swconsultoria.nfe.schema_4.retConsSitNFe.TRetConsSitNFe;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import br.com.swconsultoria.nfe.util.CancelamentoUtil;
import br.com.swconsultoria.nfe.util.RetornoUtil;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRXmlDataSource;

@Service
public class NFeService {
	
	@Autowired
	private NotaFiscalRepository repository;
	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private ConfiguracaoService configuracaoService;
	@Autowired
	private StorageService storageService;
	
	public byte[] gerarPDF(Long[] idNota) {
		try {

			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("LOGO", System.getProperty("user.home")+"/EIASisCon/logo.png");
			/**
			JasperDesign design = JRXmlLoader.load(getClass().getResourceAsStream("/relatorios/danfeRetrato.jrxml"));			
			JasperReport jasperReport = JasperCompileManager.compileReport(design);
			*/
			InputStream jasperReport = getClass().getResourceAsStream("/relatorios/danfeRetrato.jasper");
			
			JasperPrint paginas = JasperFillManager.fillReport(jasperReport, parametros,new JREmptyDataSource());
			paginas.removePage(0);
			
			for(Long id : idNota) {
				NotaFiscal nf = repository.findById(id).get();
				if(nf.getXml() != null) {
					InputStream stream = new ByteArrayInputStream(nf.getXml().getBytes());
					JRXmlDataSource xmlPath = new JRXmlDataSource(stream, "/nfeProc/NFe/infNFe/det");

					JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("/relatorios/danfeRetrato.jasper"), parametros, xmlPath);

					List<JRPrintPage> pgs = jp.getPages();
					for (JRPrintPage pg : pgs) {
						paginas.addPage(pg);
					}
				}
			}
			
			return JasperExportManager.exportReportToPdf(paginas);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	public byte[] GerarDanfe(Long idNota) {
		NotaFiscal nf = repository.findById(idNota).get();
		NFeGeradorDanfe gen= new NFeGeradorDanfe();
		
		byte[] retorn = gen.gerarPDF(nf.getXml());
		return retorn;
	}
	
	private ConfiguracoesNfe getConfig(Long empresaID) {
		Certificado certificado = new Certificado();
		ConfiguracoesNfe config = null;
		try {
			Empresa empresa = empresaRepository.findById(empresaID).get();
			Configuracao configuracao = configuracaoService.getConfiguracao(empresaID);
			
			certificado = CertificadoService.certificadoPfx(storageService.getPath(empresaID.toString(), configuracao.getCertificadoFile()), configuracao.getCertificadoSenha());  
			config = ConfiguracoesNfe.criarConfiguracoes(
					EstadosEnum.valueOf(empresa.getMunicipio().getUf().toString()),
					AmbienteEnum.PRODUCAO,
	                certificado,
	                MethodHandles.lookup().lookupClass().getResource("/schemas").getPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return config;
	}
	
	public TRetConsStatServ statusServico(Long empresaID) {
		TRetConsStatServ retorno = null;
		try {
            retorno = Nfe.statusServico(getConfig(empresaID), DocumentoEnum.NFE);
            System.out.println("Status:" + retorno.getCStat());
            System.out.println("Motivo:" + retorno.getXMotivo());
            System.out.println("Data:" + retorno.getDhRecbto());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }	
		return retorno;
	}
	
	public NFeDTO enviar(NotaFiscal nf) {
		String cnpj = nf.getEmpresa().getNumDoc().replaceAll("[^0-9,]", "");
		String uf = nf.getEmitente().getMunicipio().getUf().toString();
		
		NFeDTO retorno = new NFeDTO();		
		retorno.setUf(uf);
		retorno.setNumDoc(cnpj);
		
		ConfiguracoesNfe config = getConfig(nf.getEmpresa().getId());
		
		try {
            TNFe nfe = NFeConversor.getnFe(nf, config.getAmbiente().getCodigo());

            TEnviNFe enviNFe = new TEnviNFe();
            enviNFe.setVersao("4.00");
            enviNFe.setIdLote("1");
            enviNFe.setIndSinc("0");
            enviNFe.getNFe().add(nfe);
            
            enviNFe = Nfe.montaNfe(config, enviNFe, false);
            
            nf.setXml(XmlNfeUtil.objectToXml(enviNFe));
            repository.save(nf);
            
            TRetEnviNFe retornoEnviNFe = Nfe.enviarNfe(config, enviNFe, DocumentoEnum.NFE);
            retorno.setData(retornoEnviNFe.getDhRecbto());
            retorno.setStatus(retornoEnviNFe.getCStat());
            retorno.setMotivo(retornoEnviNFe.getXMotivo());
            retorno.setSucesso(true);
            retorno.setIdNota(nf.getId());
            retorno.setEmpresaID(nf.getEmpresa().getId());
            
            if (!retornoEnviNFe.getCStat().equals(StatusEnum.LOTE_RECEBIDO.getCodigo())) {
            	retorno.setSucesso(false);
            	retorno.setData(nf.getXml());
                throw new NfeException("Status:" + retornoEnviNFe.getCStat() + " - Motivo:" + retornoEnviNFe.getXMotivo());
            }
            retorno.setRecibo(retornoEnviNFe.getInfRec().getNRec());
        } catch (NfeException | JAXBException e) {
        	retorno.setMotivo(e.getMessage());
            System.out.println("Erro:" + e.getMessage());
        }	
		return retorno;
	}
	
	public NFeDTO consultaRecibo(NFeDTO retornoEnvio) {
		NFeDTO retornar = new NFeDTO();
		
		try {
			ConfiguracoesNfe config = getConfig(retornoEnvio.getEmpresaID());
			
			TRetConsReciNFe retornoNfe;
	        while (true) {
	            retornoNfe = Nfe.consultaRecibo(config, retornoEnvio.getRecibo(), DocumentoEnum.NFE);
	            if (retornoNfe.getCStat().equals(StatusEnum.LOTE_EM_PROCESSAMENTO.getCodigo())) {
	                System.out.println("Lote Em Processamento, vai tentar novamente apos 2 Segundo.");
	                Thread.sleep(2000);
	                continue;
	            } else {
	                break;
	            }
	        }
	        
	        retornar.setSucesso(true);
	        retornar.setData(retornoNfe.getDhRecbto());
	        retornar.setStatus(retornoNfe.getProtNFe().get(0).getInfProt().getCStat());
	        retornar.setMotivo(retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo());
	        retornar.setRecibo(retornoNfe.getProtNFe().get(0).getInfProt().getNProt());

	        if (!retornoNfe.getCStat().equals(StatusEnum.LOTE_PROCESSADO.getCodigo())) {
	        	retornar.setSucesso(false);
	            throw new NfeException("Status:" + retornoNfe.getCStat() + " - " + retornoNfe.getXMotivo());
	        }
	        if (!retornoNfe.getProtNFe().get(0).getInfProt().getCStat().equals(StatusEnum.AUTORIZADO.getCodigo()) && !retornoNfe.getProtNFe().get(0).getInfProt().getCStat().equals("204")) {
	        	retornar.setSucesso(false);
	        	retornar.setStatus(retornoNfe.getProtNFe().get(0).getInfProt().getCStat());
		        retornar.setMotivo(retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo());
	            throw new NfeException("Status:" + retornoNfe.getProtNFe().get(0).getInfProt().getCStat() + " - " + retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo());
	        }
	        
	        if(retornoNfe.getProtNFe().get(0).getInfProt().getCStat().equals("204")) {
	        	retornoEnvio.setRecibo(retornoNfe.getProtNFe().get(0).getInfProt().getXMotivo().replaceAll("[^0-9,]", "").substring(44, 59));
	        	consultaRecibo(retornoEnvio);
	        } else if(retornoNfe.getProtNFe().get(0).getInfProt().getCStat().equals("205")){
	        	NotaFiscal nf = repository.findById(retornoEnvio.getIdNota()).get();
		        
		        TEnviNFe enviNFe = XmlNfeUtil.xmlToObject(nf.getXml(), TEnviNFe.class);
		        
		        String xmlProc = XmlNfeUtil.criaNfeProc(enviNFe, retornoNfe.getProtNFe().get(0));
		        nf.setXml(xmlProc);
		        nf.setSitNfe("Denegada");
		        repository.save(nf);

		        retornar.setData(xmlProc);
	        } else if(retornoNfe.getProtNFe().get(0).getInfProt().getCStat().equals("302")){
	        	NotaFiscal nf = repository.findById(retornoEnvio.getIdNota()).get();
		        
		        TEnviNFe enviNFe = XmlNfeUtil.xmlToObject(nf.getXml(), TEnviNFe.class);
		        
		        String xmlProc = XmlNfeUtil.criaNfeProc(enviNFe, retornoNfe.getProtNFe().get(0));
		        nf.setXml(xmlProc);
		        nf.setSitNfe("Denegada");
		        repository.save(nf);

		        retornar.setData(xmlProc);
	        } else {
	        	NotaFiscal nf = repository.findById(retornoEnvio.getIdNota()).get();
		        
		        TEnviNFe enviNFe = XmlNfeUtil.xmlToObject(nf.getXml(), TEnviNFe.class);
		        
		        String xmlProc = XmlNfeUtil.criaNfeProc(enviNFe, retornoNfe.getProtNFe().get(0));
		        nf.setXml(xmlProc);
		        nf.setSitNfe("Autorizada");
		        repository.save(nf);

		        retornar.setData(xmlProc);
	        }
	        
		} catch (NfeException | JAXBException | InterruptedException e) {
			retornar.setMotivo(e.getMessage());
            System.out.println("Erro:" + e.getMessage());
        }
				
		return retornar;
	}

	public NFeDTO cancelar(NFeDTO cancelamento) {
		NFeDTO retornar = new NFeDTO();
		
		try {			
			NotaFiscal nf = repository.findById(cancelamento.getIdNota()).get();
			
			String cnpj = nf.getEmpresa().getNumDoc().replaceAll("[^0-9,]", "");
			
			ConfiguracoesNfe config = getConfig(cancelamento.getEmpresaID());
			
			TNfeProc nfeProc = XmlNfeUtil.xmlToObject(nf.getXml(), TNfeProc.class);
						
			String chave = nf.getChave().replaceAll("[^0-9,]", "");
            String protocolo = nfeProc.getProtNFe().getInfProt().getNProt();
            
            Evento cancela = new Evento();
            cancela.setChave(chave);
            cancela.setProtocolo(protocolo);
            cancela.setCnpj(cnpj);
            cancela.setMotivo(cancelamento.getMotivo());
            cancela.setDataEvento(LocalDateTime.now());

            TEnvEvento enviEvento = CancelamentoUtil.montaCancelamento(cancela, config);

            TRetEnvEvento retorno = Nfe.cancelarNfe(config, enviEvento, false, DocumentoEnum.NFE);

            RetornoUtil.validaCancelamento(retorno);

            String xmlProcEventoNFe = CancelamentoUtil.criaProcEventoCancelamento(config, enviEvento, retorno.getRetEvento().get(0));
                        
            if(nf.getProcEventoNFe() == null) { nf.setProcEventoNFe(new ArrayList<String>()); }
            nf.getProcEventoNFe().add(xmlProcEventoNFe);
            nf.setSitNfe("Cancelada");
            repository.save(nf);

            retornar.setIdNota(cancelamento.getIdNota());
            retornar.setSucesso(true);
            retornar.setStatus(retorno.getRetEvento().get(0).getInfEvento().getCStat());
            retornar.setMotivo(retorno.getRetEvento().get(0).getInfEvento().getXMotivo());
	        retornar.setData(xmlProcEventoNFe);
		} catch (NfeException | JAXBException e) {
			retornar.setMotivo(e.getMessage());
            System.out.println("Erro:" + e.getMessage());
        }
				
		return retornar;
	}
	
	public NFeDTO consultar(NFeDTO consulta) {
		NFeDTO retornar = new NFeDTO();
		
		try {			
			NotaFiscal nf = repository.findById(consulta.getIdNota()).get();
			
			ConfiguracoesNfe config = getConfig(consulta.getEmpresaID());
			
			String chave = nf.getChave();
            TRetConsSitNFe retorno = Nfe.consultaXml(config, chave, DocumentoEnum.NFE);
                        
            String xmlRetorno = XmlNfeUtil.objectToXml(retorno);
            
            retornar.setIdNota(consulta.getIdNota());
            retornar.setSucesso(true);
            retornar.setStatus(retorno.getCStat());
            retornar.setMotivo(retorno.getXMotivo());
	        retornar.setData(xmlRetorno);
		} catch (NfeException | JAXBException e) {
			retornar.setMotivo(e.getMessage());
            System.out.println("Erro:" + e.getMessage());
        }
				
		return retornar;
	}
		
}