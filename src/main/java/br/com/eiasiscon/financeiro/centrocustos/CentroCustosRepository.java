package br.com.eiasiscon.financeiro.centrocustos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroCustosRepository extends MongoRepository<CentroCustos, String>, CentroCustosRepositoryCustom {
}
