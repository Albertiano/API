package br.com.eiasiscon.pdv.config;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigPdvRepository extends MongoRepository<ConfigPdv, String>, ConfigPdvRepositoryCustom {
}
