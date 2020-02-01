package br.com.eiasiscon.financeiro.planocontas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoContasRepository extends JpaRepository<PlanoContas, Long>, PlanoContasRepositoryCustom {
}
