package br.com.eiasiscon.financeiro.centrocustos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroCustosRepository extends JpaRepository<CentroCusto, Long>, CentroCustosRepositoryCustom {
}
