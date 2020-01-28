package br.com.eiasiscon.pais;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

	@Query("FROM Pais p WHERE LOWER(p.xPais) like %:q%")
	Page<Pais> find(@Param("q") String q, Pageable pageable);
}