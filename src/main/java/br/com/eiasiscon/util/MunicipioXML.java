package br.com.eiasiscon.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.eiasiscon.municipio.Municipio;
import br.com.eiasiscon.municipio.UF;

public class MunicipioXML {
	public List<Municipio> realizaLeituraXML(){
		List<Municipio> municipios = new ArrayList<Municipio>();
		try{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(getClass().getResourceAsStream("/municipios.xml"));
        Element raiz = document.getDocumentElement();
        NodeList listaContatos = raiz.getElementsByTagName("municipio");
        
        for (int i = 0; i < listaContatos.getLength(); i++) {
            Municipio p = new Municipio();
                Element element = (Element) listaContatos.item(i);

                if(obterValorElemento(element, "cMun")!=null){
                    p.setcMun(Integer.parseInt(obterValorElemento(element, "cMun")));
                }
                if(obterValorElemento(element, "xMun")!=null){
                    p.setxMun(obterValorElemento(element, "xMun"));
                }
                if(obterValorElemento(element, "UF")!=null){
                    p.setUf(UF.valueOf((obterValorElemento(element, "UF"))));
                }
                municipios.add(p);      
        }
		}catch(Exception e){
			e.printStackTrace();
		}
        return municipios;
    }
    public String obterValorElemento(Element elemento, String nomeElemento){
		
		NodeList listaElemento = elemento.getElementsByTagName(nomeElemento);
		if (listaElemento == null){
			return null;
		}
		
		Element noElemento = (Element) listaElemento.item(0);
		if (noElemento == null){
			return null;
		}
		
		Node no = noElemento.getFirstChild();
                if (no == null){
			return null;
		}
		return no.getNodeValue();
	}
}

