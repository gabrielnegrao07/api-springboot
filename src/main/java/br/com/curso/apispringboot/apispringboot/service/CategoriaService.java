package br.com.curso.apispringboot.apispringboot.service;

import br.com.curso.apispringboot.apispringboot.domain.Categoria;
import br.com.curso.apispringboot.apispringboot.dto.CategoriaDTO;
import br.com.curso.apispringboot.apispringboot.repositories.CategoriaRepository;
import br.com.curso.apispringboot.apispringboot.service.Exception.DataIntegrityException;
import br.com.curso.apispringboot.apispringboot.service.Exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Categoria update(Categoria categoriaUp){
        Categoria categoria = find(categoriaUp.getId());
        updateData(categoria, categoriaUp);
        return categoriaRepository.save(categoria);
    }

    public void delete(Integer id){
        find(id);
        try{
            categoriaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
        }
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO obj){
        return new Categoria(obj.getId(), obj.getNome());
    }

    private void updateData(Categoria categoria, Categoria categoriaUp){
        categoria.setNome(categoriaUp.getNome());
    }

}
