package br.com.eiasiscon.contato;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.eiasiscon.contato.Contato;

@Repository
public interface ContatoRepository extends MongoRepository<Contato, String>, ContatoRepositoryCustom {
}
