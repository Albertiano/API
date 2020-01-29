package br.com.eiasiscon.empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eiasiscon.empresa.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>, EmpresaRepositoryCustom {	

}
