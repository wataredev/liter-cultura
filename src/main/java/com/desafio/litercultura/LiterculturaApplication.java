package com.desafio.litercultura;

import com.desafio.litercultura.principal.Principal;
import com.desafio.litercultura.repository.AutorRepository;
import com.desafio.litercultura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterculturaApplication implements CommandLineRunner {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterculturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(livroRepository, autorRepository);
		principal.exibirMenu();
	}
}
