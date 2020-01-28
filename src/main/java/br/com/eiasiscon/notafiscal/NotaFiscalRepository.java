package br.com.eiasiscon.notafiscal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotaFiscalRepository extends MongoRepository<NotaFiscal, String>, NotaFiscalRepositoryCustom {
}
