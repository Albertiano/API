package br.com.eiasiscon.financeiro.lancamento;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.eiasiscon.base.BaseService;
import br.com.eiasiscon.financeiro.conta.Conta;
import br.com.eiasiscon.financeiro.conta.ContaRepository;

@Service
public class LancamentoService extends BaseService<Lancamento, Long> {
	
	@Autowired
	private LancamentoRepository repository;
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	public void setJpaRepository(LancamentoRepository jpa) {
		setJpa(jpa);
	}
	
	public Page<Lancamento> find(LancamentoFiltro filtro, Pageable pageable) {
		Page<Lancamento>  entities = repository.find(filtro, pageable);
		return entities;
	}
	
	public int totalPages(LancamentoFiltro filtro, Pageable pageable) {
		return repository.totalPages(filtro, pageable);
	}
	
	public Lancamento atualizarConta(Lancamento lancamento) {
		Conta conta = contaRepository.findById(lancamento.getConta().getId()).get();
		
		if(lancamento.getTpLancamento() == TpLancamento.CREDITO) {
			BigDecimal saldo = conta.getSaldo();
			saldo = saldo.add(lancamento.getValor());
			conta.setSaldo(saldo);
			contaRepository.save(conta);
			lancamento.setConta(conta);
		} else {
			BigDecimal saldo = conta.getSaldo();
			saldo = saldo.subtract(lancamento.getValor());
			conta.setSaldo(saldo);
			contaRepository.save(conta);
			lancamento.setConta(conta);
		}
		return lancamento;
	}
	
	public Lancamento novo(Long empresa) {
		Lancamento last = repository.ultimo(empresa);
		if (last == null) {
			last = new Lancamento();
		}
		last.setId(null);
		last.setDescricao(null);
		last.setCompetencia(new Date());
		last.setCreatedAt(new Date());
		last.setUpdatedAt(new Date());
		last.setDocumento(null);
		last.setValor(BigDecimal.ZERO);
		return last;
	}
}

