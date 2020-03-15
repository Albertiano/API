package br.com.eiasiscon.produto.tributacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.eiasiscon.produto.tributacao.Tributacao;

public interface TributacaoRepositoryCustom {

	Page<Tributacao> find(String q, Long empresa, Pageable page);
}
