package br.com.curso.apispringboot.apispringboot.service;

import br.com.curso.apispringboot.apispringboot.domain.Cidade;
import br.com.curso.apispringboot.apispringboot.domain.Cliente;
import br.com.curso.apispringboot.apispringboot.domain.Endereco;
import br.com.curso.apispringboot.apispringboot.domain.enums.TipoCliente;
import br.com.curso.apispringboot.apispringboot.dto.ClienteDTO;
import br.com.curso.apispringboot.apispringboot.dto.ClienteNewDTO;
import br.com.curso.apispringboot.apispringboot.repositories.ClienteRepository;
import br.com.curso.apispringboot.apispringboot.repositories.EnderecoRepository;
import br.com.curso.apispringboot.apispringboot.service.Exception.DataIntegrityException;
import br.com.curso.apispringboot.apispringboot.service.Exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id){
      return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
              "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente cliente){
//      Obs: O mais correto seria criar uma classe de request com dados de envio.
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }

    public Cliente update(Cliente clienteUp){
        Cliente cliente = find(clienteUp.getId());
        updateData(cliente, clienteUp);
        return clienteRepository.save(cliente);
    }

    public void delete(Integer id){
        find(id);
        try{
            clienteRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir por há entidades relacionadas.");
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO obj){
        return new Cliente(obj.getId(), obj.getNome(), obj.getEmail(), null, null);
    }

    public Cliente fromDTO(ClienteNewDTO obj){
        Cliente cli = new Cliente(null, obj.getNome(), obj.getEmail(), obj.getCpfOuCnpj(), TipoCliente.toEnum(obj.getTipo()));
        Cidade cid = new Cidade(obj.getCidadeId(), null, null);
        Endereco end = new Endereco(null, obj.getLogradouro(), obj.getNumero(), obj.getComplemento(), obj.getBairro(), obj.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(obj.getTelefone1());
        if (obj.getTelefone2() != null){
            cli.getTelefones().add(obj.getTelefone2());
        }
        if (obj.getTelefone3() != null){
            cli.getTelefones().add(obj.getTelefone3());
        }
        return cli;
    }



    private void updateData(Cliente cliente, Cliente clienteUp){
        cliente.setNome(clienteUp.getNome());
        cliente.setEmail(clienteUp.getEmail());
    }
}
