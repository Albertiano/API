package br.com.eiasiscon.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.eiasiscon.pais.Pais;

public class PaisXML {

	public String obterValorElemento(Element elemento, String nomeElemento) {

		NodeList listaElemento = elemento.getElementsByTagName(nomeElemento);
		if (listaElemento == null) {
			return null;
		}

		Element noElemento = (Element) listaElemento.item(0);
		if (noElemento == null) {
			return null;
		}

		Node no = noElemento.getFirstChild();
		if (no == null) {
			return null;
		}
		return no.getNodeValue();
	}

	public List<Pais> realizaLeituraXML() {
		List<Pais> paises = new ArrayList<Pais>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(getClass().getResourceAsStream("/paises.xml"));
			Element raiz = document.getDocumentElement();
			NodeList listElements = raiz.getElementsByTagName("pais");

			for (int i = 0; i < listElements.getLength(); i++) {
				int cPais = 0;
				String xPais = null;

				Element element = (Element) listElements.item(i);

				if (obterValorElemento(element, "codigo") != null) {
					cPais = Integer.parseInt(obterValorElemento(element, "codigo"));
				}
				if (obterValorElemento(element, "nome") != null) {
					xPais = obterValorElemento(element, "nome");
				}
				paises.add(Pais.builder().cPais(cPais).xPais(xPais).build());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paises;
	}

	public List<Pais> realizaLeituraXML(InputStream stream) {
		List<Pais> paises = new ArrayList<Pais>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(stream);
			Element raiz = document.getDocumentElement();
			NodeList listElements = raiz.getElementsByTagName("pais");

			for (int i = 0; i < listElements.getLength(); i++) {
				int cPais = 0;
				String xPais = null;

				Element element = (Element) listElements.item(i);

				if (obterValorElemento(element, "codigo") != null) {
					cPais = Integer.parseInt(obterValorElemento(element, "codigo"));
				}
				if (obterValorElemento(element, "nome") != null) {
					xPais = obterValorElemento(element, "nome");
				}
				paises.add(Pais.builder().cPais(cPais).xPais(xPais).build());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paises;
	}

}
