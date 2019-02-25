package br.com.curso.apispringboot.apispringboot.repositories;

import br.com.curso.apispringboot.apispringboot.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
