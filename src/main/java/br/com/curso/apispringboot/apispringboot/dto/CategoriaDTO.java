package br.com.curso.apispringboot.apispringboot.dto;

import br.com.curso.apispringboot.apispringboot.domain.Categoria;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1l;

    private Integer id;
    private String nome;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
