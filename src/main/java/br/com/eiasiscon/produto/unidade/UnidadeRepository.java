package br.com.eiasiscon.produto.unidade;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.eiasiscon.produto.unidade.Unidade;


@Repository
public interface UnidadeRepository extends MongoRepository<Unidade, String>, UnidadeRepositoryCustom {
}
