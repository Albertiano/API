package br.com.eiasiscon.financeiro.lancamento;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends MongoRepository<Lancamento, String>, LancamentoRepositoryCustom {
}
