package com.desafio.litercultura.repository;

import com.desafio.litercultura.model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autores, Long> {
}
