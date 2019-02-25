package br.com.curso.apispringboot.apispringboot.service;

import br.com.curso.apispringboot.apispringboot.domain.Categoria;
import br.com.curso.apispringboot.apispringboot.domain.Cliente;
import br.com.curso.apispringboot.apispringboot.repositories.ClienteRepository;
import br.com.curso.apispringboot.apispringboot.service.Exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id){
      return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
              "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
}
