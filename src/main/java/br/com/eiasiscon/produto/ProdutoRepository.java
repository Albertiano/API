package br.com.eiasiscon.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eiasiscon.produto.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryCustom {	

}
