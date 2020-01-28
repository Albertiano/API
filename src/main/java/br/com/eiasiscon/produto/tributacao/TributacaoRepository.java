package br.com.eiasiscon.produto.tributacao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.eiasiscon.produto.tributacao.Tributacao;

@Repository
public interface TributacaoRepository extends MongoRepository<Tributacao, String>, TributacaoRepositoryCustom {

}
