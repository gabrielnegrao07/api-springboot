package br.com.curso.apispringboot.apispringboot.repository;

import br.com.curso.apispringboot.apispringboot.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
