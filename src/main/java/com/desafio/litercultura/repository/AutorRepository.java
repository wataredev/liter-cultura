package com.desafio.litercultura.repository;

import com.desafio.litercultura.model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autores, Long> {
    Optional<Autores> findByNomeAutor(String nomeAutor);

    List<Autores> getAutoresByAnoNascimentoGreaterThan(int ano);
}
