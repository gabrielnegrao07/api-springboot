package br.com.curso.apispringboot.apispringboot.repositories;

import br.com.curso.apispringboot.apispringboot.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
