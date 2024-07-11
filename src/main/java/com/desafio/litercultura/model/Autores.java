package com.desafio.litercultura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeAutor;
    private Integer anoNascimento;
    private Integer anoMorte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Livros> livro = new ArrayList<>();

    public Autores() {}

    public Autores(DadosAutores dadosAutores) {
        this.nomeAutor = dadosAutores.nomeAutor();
        this.anoNascimento = dadosAutores.anoNascimento();
        this.anoMorte = dadosAutores.anoNascimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(Integer anoMorte) {
        this.anoMorte = anoMorte;
    }

    public List<Livros> getLivro() {
        return livro;
    }

    @Override
    public String toString() {
        return   getNomeAutor() +
                " *Ano Nascimento: " + getAnoNascimento() + "*" +
                " *Ano de Morte: " + getAnoMorte() + "*";
    }
}
