package br.com.eiasiscon.empresa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.eiasiscon.empresa.Empresa;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String>, EmpresaRepositoryCustom {	

}
