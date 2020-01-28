package br.com.eiasiscon.nfe;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRXmlDataSource;

public class NFeGeradorDanfe {
	
	public byte[] gerarPDF(String xml) {
		InputStream stream = null;
		JRXmlDataSource xmlPath = null;
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
			stream = new ByteArrayInputStream(xml.getBytes());
			xmlPath = new JRXmlDataSource(stream, "/nfeProc/NFe/infNFe/det");

			JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("/relatorios/danfeRetrato.jasper"), parametros, xmlPath);

			List<JRPrintPage> pgs = jp.getPages();
			for (JRPrintPage pg : pgs) {
				paginas.addPage(pg);
			}
			return JasperExportManager.exportReportToPdf(paginas);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
