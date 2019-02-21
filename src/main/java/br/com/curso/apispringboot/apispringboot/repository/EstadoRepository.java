package br.com.curso.apispringboot.apispringboot.repository;

import br.com.curso.apispringboot.apispringboot.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
