package br.com.eiasiscon.notafiscal;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eiasiscon.municipio.UF;
import br.com.eiasiscon.nfe.common.NFeChaveAcesso;
import br.com.eiasiscon.notafiscal.ide.FinNFe;
import br.com.eiasiscon.notafiscal.ide.IdDest;
import br.com.eiasiscon.notafiscal.ide.IndFinal;
import br.com.eiasiscon.notafiscal.ide.IndPres;
import br.com.eiasiscon.notafiscal.ide.TpNF;
import br.com.eiasiscon.notafiscal.item.ItemNotaFiscal;
import br.com.eiasiscon.notafiscal.pagamento.IndPag;
import br.com.eiasiscon.notafiscal.pagamento.TpPag;
import br.com.eiasiscon.notafiscal.transporte.ModFrete;

@CrossOrigin
@RestController
@RequestMapping("/nota-fiscal")
public class NotaFiscalEndpoint {
	
	@Autowired
	private NotaFiscalService service;
		
	@GetMapping
	public Page<NotaFiscal> procurar(@RequestParam String filter, @RequestParam Long empresa, Pageable pageable) {
		Page<NotaFiscal> contatos =  service.find(filter, empresa, pageable);
		return contatos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NotaFiscal> recuperar(@PathVariable Long id) {
		NotaFiscal entity = service.retrieve(id);
		return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<NotaFiscal> criar(@Valid @RequestBody NotaFiscal entity, HttpServletResponse response) {
		if( entity.getMod().equals("55")) {
			if (entity.getSerie() == null || entity.getSerie() == 0) {
				entity.setSerie(service.getSerieNFe(entity.getPdv().getId()));
			}
			if (entity.getNumero() == null || entity.getNumero() == 0) {
				entity.setNumero(service.getNextNumeroNFe(entity.getPdv().getId()));
			}
		} else {
			if (entity.getSerie() == null || entity.getSerie() == 0) {
				entity.setSerie(service.getSerieNFCe(entity.getPdv().getId()));
			}
			if (entity.getNumero() == null || entity.getNumero() == 0) {
				entity.setNumero(service.getNextNumeroNFCe(entity.getPdv().getId()));
			}
		}
		entity.setChave(NFeChaveAcesso.getChave(entity));
		
		NotaFiscal entitySaved = service.create(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entitySaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<NotaFiscal> atualizar(@PathVariable Long id, @Valid @RequestBody NotaFiscal entity) {
		NotaFiscal entitySaved = service.update(id, entity);
		return ResponseEntity.ok(entitySaved);
	}
	
	@GetMapping("/duplicar/{id}")
	public ResponseEntity<NotaFiscal> duplicar(@PathVariable Long id) {
		NotaFiscal entitySaved = service.duplicar(id);
		return entitySaved != null ? ResponseEntity.status(HttpStatus.CREATED).body(entitySaved) : ResponseEntity.notFound().build();
	}

	@GetMapping("/FinNFe")
	public List<FinNFe> getFinNFe() {
		List<FinNFe> res = Arrays.asList(FinNFe.values());
		return res;
	}
	
	@GetMapping("/TpNF")
	public List<TpNF> getTpNF() {
		List<TpNF> res = Arrays.asList(TpNF.values());
		return res;
	}
	
	@GetMapping("/IdDest")
	public List<IdDest> getIdDest() {
		List<IdDest> res = Arrays.asList(IdDest.values());
		return res;
	}
	
	@GetMapping("/IndFinal")
	public List<IndFinal> getIndFinal() {
		List<IndFinal> res = Arrays.asList(IndFinal.values());
		return res;
	}
	
	@GetMapping("/IndPres")
	public List<IndPres> getIndPres() {
		List<IndPres> res = Arrays.asList(IndPres.values());
		return res;
	}
	
	@GetMapping("/ModFrete")
	public List<ModFrete> getModFrete() {
		List<ModFrete> res = Arrays.asList(ModFrete.values());
		return res;
	}
	
	@GetMapping("/IndPag")
	public List<IndPag> getIndPag() {
		List<IndPag> res = Arrays.asList(IndPag.values());
		return res;
	}
	
	@GetMapping("/TpPag")
	public List<TpPag> getTpPag() {
		List<TpPag> res = Arrays.asList(TpPag.values());
		return res;
	}
	
	@GetMapping("/item")
	public ItemNotaFiscal getItem(@RequestParam Long idProd, @RequestParam BigDecimal quant, @RequestParam BigDecimal vUn, UF uf) {
		ItemNotaFiscal item = service.getItem(idProd, quant, vUn, uf);
		return item;
	}
	
	@PostMapping(value = "/zip", produces = "application/zip")
	public ResponseEntity<byte[]> gerarZip(@RequestBody NotaFiscalDTO dto, HttpServletResponse response) {
		
		byte[] retorn = service.gerarZip(dto.getIni(), dto.getFim(), dto.getEmpresa());
		
		return retorn != null ? ResponseEntity.ok(retorn) : ResponseEntity.notFound().build();
	}
	
	@PostMapping(value = "/exportar", produces = "application/zip")
	public ResponseEntity<byte[]> exportar(@RequestBody Long[] idNota) {
		
		byte[] retorn = service.exportar(idNota);
		
		return retorn != null ? ResponseEntity.ok(retorn) : ResponseEntity.notFound().build();
	}
	
	@GetMapping(value = "/exportar", produces = "application/zip")
	public ResponseEntity<byte[]> exportar(@RequestParam Long idNota) {
		
		byte[] retorn = service.exportar(idNota);
		
		return retorn != null ? ResponseEntity.ok(retorn) : ResponseEntity.notFound().build();
	}

}
