package br.com.eiasiscon.ibpt;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/ibpt")
public class IBPTEndpoint {

	@Autowired
	IBPTRepository repository;

	@DeleteMapping("/all")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover() {
		repository.deleteAll();
	}

	@PostMapping
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			
			InputStream is = null;
			BufferedReader br = null;

	        try {	        					
				is = file.getInputStream();
		        br = new BufferedReader(new InputStreamReader(is)); 

				CSVParser parser = CSVFormat.DEFAULT.withDelimiter(';').withHeader().parse(br);

		        for (CSVRecord record : parser) {
		            String codigo = record.get("codigo").trim();
		            String tipo = record.get("tipo").trim();
		            String descricao = record.get("descricao").trim();                
		            String nacionalFederal = record.get("nacionalfederal");
		            String importadosFederal = record.get("importadosfederal").trim();
		            String estadual = record.get("estadual").trim();
		            String municipal = record.get("municipal").trim();
		            String vigenciaInicio = record.get("vigenciainicio").trim();
		            String vigenciaFim = record.get("vigenciafim").trim();
		            String chave = record.get("chave").trim();
		            String versao = record.get("versao").trim();
		            String fonte = record.get("fonte").trim();
		            
		            if(tipo.equalsIgnoreCase("0")) {
		            	DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
		            	
    		            IBPT ibpt = IBPT.builder().build();    		            
    		            ibpt.setCodigo(codigo);
    		            ibpt.setTipo(tipo);
    		            ibpt.setDescricao(descricao);
    		            ibpt.setNacionalFederal(Double.parseDouble(nacionalFederal));
    		            ibpt.setImportadosFederal(Double.parseDouble(importadosFederal));
    		            ibpt.setEstadual(Double.parseDouble(estadual));
    		            ibpt.setMunicipal(Double.parseDouble(municipal));    		            
    		            ibpt.setVigenciaInicio(LocalDate.parse(vigenciaInicio, formatter));
    		            ibpt.setVigenciaFim(LocalDate.parse(vigenciaFim, formatter));
    		            ibpt.setChave(chave);
    		            ibpt.setVersao(versao);
    		            ibpt.setFonte(fonte);
    		            
    		            repository.save(ibpt);
		            }
		        }
	            
	            return new ResponseEntity<Object>(HttpStatus.OK);

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		}
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

	}
}
