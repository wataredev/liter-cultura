package com.desafio.litercultura.repository;
import com.desafio.litercultura.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livros, Long> {
    Optional<Livros> findByTitulo(String titulo);

    List<Livros> getLivrosByIdioma(String idiomaEscolhido);
}
