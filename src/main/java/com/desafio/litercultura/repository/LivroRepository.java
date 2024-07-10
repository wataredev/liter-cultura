package com.desafio.litercultura.repository;
import com.desafio.litercultura.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livros, Long> {
}
