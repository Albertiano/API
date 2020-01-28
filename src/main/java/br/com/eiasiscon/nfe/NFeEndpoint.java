package br.com.eiasiscon.nfe;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eiasiscon.nfe.common.NFeDTO;
import br.com.eiasiscon.notafiscal.NotaFiscal;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TRetConsStatServ;

@CrossOrigin
@RestController
@RequestMapping("/nfe")
public class NFeEndpoint {
	
	@Autowired
	private NFeService service;
	
	@GetMapping("/status")
	public ResponseEntity<TRetConsStatServ> statusServico(@RequestParam String empresaID) {
		
		TRetConsStatServ retorn = service.statusServico(empresaID);
		
		return retorn != null ? ResponseEntity.ok(retorn) : ResponseEntity.notFound().build();				
	}
	
	@GetMapping(value = "/danfe", produces = "application/pdf")
	public ResponseEntity<byte[]> danfe(@RequestParam String idNota) {
		
		byte[] retorn = service.GerarDanfe(idNota);
		
		return retorn != null ? ResponseEntity.ok(retorn) : ResponseEntity.notFound().build();				
	}
	
	@PostMapping(value = "/danfe", produces = "application/pdf")
	public ResponseEntity<byte[]> danfes(@RequestBody String[] idNota) {
		
		byte[] retorn = service.gerarPDF(idNota);
		
		return retorn != null ? ResponseEntity.ok(retorn) : ResponseEntity.notFound().build();				
	}
	
	@PostMapping("/enviar")
	public ResponseEntity<NFeDTO> enviar(@Valid @RequestBody NotaFiscal nf) {		
		NFeDTO retorn = service.enviar(nf);
		return ResponseEntity.status(HttpStatus.OK).body(retorn);
	}
	
	@PostMapping("/consultaRecibo")
	public ResponseEntity<NFeDTO> consultaRecibo(@RequestBody NFeDTO retornoEnvio) {		
		NFeDTO retorn = service.consultaRecibo(retornoEnvio);
		return ResponseEntity.status(HttpStatus.OK).body(retorn);
	}
	
	@PostMapping("/cancelar")
	public ResponseEntity<NFeDTO> cancelar(@RequestBody NFeDTO cancelamento) {		
		NFeDTO retorn = service.cancelar(cancelamento);
		return ResponseEntity.status(HttpStatus.OK).body(retorn);
	}
	
	@PostMapping("/consultar")
	public ResponseEntity<NFeDTO> consultar(@RequestBody NFeDTO consulta) {		
		NFeDTO retorn = service.consultar(consulta);
		return ResponseEntity.status(HttpStatus.OK).body(retorn);
	}

}
