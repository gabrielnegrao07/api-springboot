package br.com.curso.apispringboot.apispringboot.controller;

import br.com.curso.apispringboot.apispringboot.domain.Cliente;
import br.com.curso.apispringboot.apispringboot.dto.ClienteDTO;
import br.com.curso.apispringboot.apispringboot.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id){
        Cliente cliente = clienteService.find(id);
        return ResponseEntity.ok().body(cliente);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id){
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        cliente.setId(id);
        clienteService.update(cliente);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> clientes = clienteService.findAll();
        List<ClienteDTO> clienteDTOS = clientes.stream().map(obj -> new ClienteDTO(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(clienteDTOS);
    }

    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction ){
        Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }

}
