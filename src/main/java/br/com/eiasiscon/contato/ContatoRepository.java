package br.com.eiasiscon.contato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eiasiscon.contato.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>, ContatoRepositoryCustom {
}
