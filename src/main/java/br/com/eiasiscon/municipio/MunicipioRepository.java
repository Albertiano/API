package br.com.eiasiscon.municipio;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

	@Query("FROM Municipio m WHERE LOWER(m.xMun) like %:q%")
	Page<Municipio> find(@Param("q") String q, Pageable pageable);

	Collection<Municipio> findByUf(UF uf);
}
