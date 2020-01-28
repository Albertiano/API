package br.com.eiasiscon.financeiro.planocontas;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoContasRepository extends MongoRepository<PlanoContas, String>, PlanoContasRepositoryCustom {
}
