package br.com.eiasiscon.produto.unidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eiasiscon.produto.unidade.Unidade;


@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, String>, UnidadeRepositoryCustom {
}
