package br.com.curso.apispringboot.apispringboot.service;

import br.com.curso.apispringboot.apispringboot.domain.Categoria;
import br.com.curso.apispringboot.apispringboot.repositories.CategoriaRepository;
import br.com.curso.apispringboot.apispringboot.service.Exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

//    public Categoria find(Integer id){
//        Categoria categoria = categoriaRepository.findById(id).orElseGet();
//        if (categoria == null){
//            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
//                    + ", Tipo: " + Categoria.class.getName());
//        }
//        return categoria;
//    }

    public Categoria find(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria categoria){
//      Obs: O mais correto seria criar uma classe de request com dados de envio.
        categoria.setId(null);
        return categoriaRepository.save(categoria);

    }
}
