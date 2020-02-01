package br.com.eiasiscon.notafiscal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long>, NotaFiscalRepositoryCustom {
}
