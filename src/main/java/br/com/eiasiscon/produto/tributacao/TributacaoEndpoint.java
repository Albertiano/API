package br.com.eiasiscon.produto.tributacao;

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

import br.com.eiasiscon.produto.tributacao.Tributacao;
import br.com.eiasiscon.produto.tributacao.TributacaoService;
import br.com.eiasiscon.produto.tributacao.icms.CST_ICMS;
import br.com.eiasiscon.produto.tributacao.icms.ModBC;
import br.com.eiasiscon.produto.tributacao.icms.ModBCST;
import br.com.eiasiscon.produto.tributacao.icms.Origem;
import br.com.eiasiscon.produto.tributacao.ipi.CST_IPI;
import br.com.eiasiscon.produto.tributacao.ipi.TpCalcIPI;

@CrossOrigin
@RestController
@RequestMapping("/tributacao")
public class TributacaoEndpoint {

	@Autowired
	private TributacaoService service;
		
	@GetMapping
	public Page<Tributacao> procurar(@RequestParam String filter, @RequestParam String empresa, Pageable pageable) {
		Page<Tributacao> contatos =  service.find(filter, empresa, pageable);
		return contatos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tributacao> recuperar(@PathVariable String id) {
		Tributacao entity = service.retrieve(id);
		return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/new")
	public ResponseEntity<Tributacao> novo() {
		Tributacao entity = service.instance();
		return ResponseEntity.status(HttpStatus.OK).body(entity);
	}
	
	@PostMapping
	public ResponseEntity<Tributacao> criar(@Valid @RequestBody Tributacao entity, HttpServletResponse response) {
		Tributacao entitySaved = service.create(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entitySaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable String id) {
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Tributacao> atualizar(@PathVariable String id, @Valid @RequestBody Tributacao entity) {
		Tributacao entitySaved = service.update(id, entity);
		return ResponseEntity.ok(entitySaved);
	}
	
	@GetMapping("/cst-icms")
	public List<CST_ICMS> getCST_ICMS() {
		List<CST_ICMS> regs = Arrays.asList(CST_ICMS.values());
		return regs;
	}
	
	@GetMapping("/origem")
	public List<Origem> getOrigens() {
		List<Origem> regs = Arrays.asList(Origem.values());
		return regs;
	}
	
	@GetMapping("/mod-icms")
	public List<ModBC> getModB() {
		List<ModBC> regs = Arrays.asList(ModBC.values());
		return regs;
	}
	
	@GetMapping("/mod-icms-st")
	public List<ModBCST> getModBCST() {
		List<ModBCST> regs = Arrays.asList(ModBCST.values());
		return regs;
	}
	
	@GetMapping("/cst-ipi")
	public List<CST_IPI> getCSTsIpi() {
		List<CST_IPI> regs = Arrays.asList(CST_IPI.values());
		return regs;
	}
	
	@GetMapping("/tp-calc-ipi")
	public List<TpCalcIPI> getTpCalcIpi() {
		List<TpCalcIPI> regs = Arrays.asList(TpCalcIPI.values());
		return regs;
	}
}
