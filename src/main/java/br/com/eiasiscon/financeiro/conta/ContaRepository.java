package br.com.eiasiscon.financeiro.conta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>, ContaRepositoryCustom {
}
