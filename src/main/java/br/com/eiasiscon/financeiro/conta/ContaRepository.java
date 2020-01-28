package br.com.eiasiscon.financeiro.conta;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends MongoRepository<Conta, String>, ContaRepositoryCustom {
}
