package br.com.eiasiscon.produto.tributacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eiasiscon.produto.tributacao.Tributacao;

@Repository
public interface TributacaoRepository extends JpaRepository<Tributacao, Long>, TributacaoRepositoryCustom {

}
