package br.com.curso.apispringboot.apispringboot.repositories;

import br.com.curso.apispringboot.apispringboot.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

//    Para não se enquadrar como uma transação de banco de dados, ficando mais rápida e diminui o locking no gerenciamento de transações
//    no banco de dados.
    @Transactional(readOnly=true)
    Cliente findByEmail(String email);


}
