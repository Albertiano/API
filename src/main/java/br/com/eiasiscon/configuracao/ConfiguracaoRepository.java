package br.com.eiasiscon.configuracao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConfiguracaoRepository extends MongoRepository<Configuracao, String>, ConfiguracaoRepositoryCustom {
}
