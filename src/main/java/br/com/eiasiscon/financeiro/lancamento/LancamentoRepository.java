package br.com.eiasiscon.financeiro.lancamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryCustom {
}
