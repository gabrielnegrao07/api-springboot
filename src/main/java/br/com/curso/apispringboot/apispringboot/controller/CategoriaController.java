package br.com.curso.apispringboot.apispringboot.controller;

import br.com.curso.apispringboot.apispringboot.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar(){

        Categoria cat1 = new Categoria(1, "Informática");
        Categoria cat2 = new Categoria(2, "Escritório");

        List<Categoria> categoriaList = new ArrayList<>();

        categoriaList.add(cat1);
        categoriaList.add(cat2);

        return categoriaList;
    }
}
