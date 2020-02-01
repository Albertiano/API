package br.com.eiasiscon.financeiro.lancamento;

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

import br.com.eiasiscon.base.BaseEndpoint;

@CrossOrigin
@RestController
@RequestMapping("/lancamento")
public class LancamentoEndpoint extends BaseEndpoint<Lancamento, Long> {
	
	@Autowired
	private LancamentoService service;
		
	@PostMapping("/filtro")
	public Page<Lancamento> procurar(@RequestBody LancamentoFiltro filtro, Pageable pageable) {
		Page<Lancamento> contatos =  service.find(filtro, pageable);
		return contatos;
	}
	
	@PostMapping("/pages")
	public int totalPages(@RequestBody LancamentoFiltro filtro, Pageable pageable) {
		return service.totalPages(filtro, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento entity, HttpServletResponse response) {
		Lancamento entitySaved;
		if(entity.getId() == null) {
			entitySaved = service.atualizarConta(entity);
			entitySaved = service.create(entitySaved);
		} else {
			entitySaved = service.retrieve(entity.getId());
			if(entitySaved.getTpLancamento() == TpLancamento.CREDITO) {
				entitySaved.setTpLancamento(TpLancamento.DEBITO);
			} else {
				entitySaved.setTpLancamento(TpLancamento.CREDITO);
			}
			service.atualizarConta(entitySaved);
			
			entitySaved = service.atualizarConta(entity);
			entitySaved = service.create(entitySaved);
		}
				
		return ResponseEntity.status(HttpStatus.CREATED).body(entitySaved);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		Lancamento entity = service.retrieve(id);
		if(entity.getTpLancamento() == TpLancamento.CREDITO) {
			entity.setTpLancamento(TpLancamento.DEBITO);
		} else {
			entity.setTpLancamento(TpLancamento.CREDITO);
		}
		service.atualizarConta(entity);
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Lancamento> atualizar(@PathVariable Long id, @Valid @RequestBody Lancamento entity) {
		Lancamento entitySaved = service.retrieve(entity.getId());
		if(entity.getTpLancamento() == TpLancamento.CREDITO) {
			entitySaved.setTpLancamento(TpLancamento.DEBITO);
		} else {
			entitySaved.setTpLancamento(TpLancamento.CREDITO);
		}
		service.atualizarConta(entitySaved);
		
		entitySaved = service.atualizarConta(entity);
		entitySaved = service.update(id, entitySaved);
		
		return ResponseEntity.ok(entitySaved);
	}
	
	@GetMapping("/novo")
	public ResponseEntity<Lancamento> novo(@RequestParam Long empresa) {
		Lancamento entity = service.novo(empresa);
		return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/TpPagamento")
	public List<TpPagamento> getTpPagamento() {
		List<TpPagamento> res = Arrays.asList(TpPagamento.values());
		return res;
	}
	
	@GetMapping("/TpLancamento")
	public List<TpLancamento> getIndFinal() {
		List<TpLancamento> res = Arrays.asList(TpLancamento.values());
		return res;
	}

}
