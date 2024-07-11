package com.desafio.litercultura.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Integer numDownloads;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "autor_id")
    private Autores autor;


    public Livros() {}


    public Livros(DadosLivros dados) {
        this.titulo = dados.titulo();
        this.numDownloads = dados.numDownloads();
        this.idioma = dados.idioma().get(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumDownloads() {
        return numDownloads;
    }

    public void setNumDownloads(Integer numDownloads) {
        this.numDownloads = numDownloads;
    }

    public void setAutor(Autores dadosAutor) {
        this.autor = dadosAutor;
    }

    public Autores getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return " ----- LIVRO ----- "
                +"\nTítulo: " + getTitulo()
                +"\nIdioma: " + getIdioma()
                +"\nAutor: " + getAutor()
                +"\nTotal Downloads: " + getNumDownloads()
                +"\n-----------------";
    }
}
