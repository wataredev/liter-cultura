package com.desafio.litercultura.model;


import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Integer numDownloads;

    public Livros() {}

    public Livros(DadosLivros dados) {
        this.titulo = dados.titulo();
        this.numDownloads = dados.numDownloads();
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

    @Override
    public String toString() {
        return  "titulo='" + titulo + '\'' +
                ", numDownloads=" + numDownloads +
                '}';
    }
}
