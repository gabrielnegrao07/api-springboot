package br.com.curso.apispringboot.apispringboot.repositories;

import br.com.curso.apispringboot.apispringboot.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
