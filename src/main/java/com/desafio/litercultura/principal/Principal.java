package com.desafio.litercultura.principal;

import com.desafio.litercultura.model.DadosLivros;
import com.desafio.litercultura.model.DadosResult;
import com.desafio.litercultura.model.Livros;
import com.desafio.litercultura.repository.AutorRepository;
import com.desafio.litercultura.repository.LivroRepository;
import com.desafio.litercultura.service.ConsumoAPI;
import com.desafio.litercultura.service.ConverteDados;

import java.util.Scanner;

public class Principal {
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private final LivroRepository livroRepositorio;
    private final AutorRepository autorRepository;

    Scanner scanner = new Scanner(System.in);

    private final String ENDERECO = "https://gutendex.com/books?search=";

    public Principal(LivroRepository repositorio, AutorRepository autorRepository) {
        this.livroRepositorio = repositorio;
        this.autorRepository = autorRepository;
    }

    public void exibirMenu() {
        var escolha = -1;

        while (escolha != 0) {
        System.out.println("""
                *********** Bem-Vindos ao Liter-Cultura! ***********
                
                ----- Digite o número correspondente a operação ----
               
                1- Buscar livro pelo título
                2- Listar livros registrados
                3- Listar autores registrados
                4- Listar autores vivos em um determinado ano
                5- Listar livros em um determinado idioma
                
                0- Sair
                
                ****************************************************
                """);
        var opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                buscarLivro();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                System.out.println("Digite uma opção válida!");
        }

        }
    }

    public void buscarLivro() {

        System.out.println("Digite o nome do livro !");
        String nomeLivro = scanner.nextLine().toLowerCase().replace(" ", "%20");
        var json = consumo.obterDados("https://gutendex.com/books?search=dom%20casmurro");
        DadosResult dadosResult = conversor.obterDados(json, DadosResult.class);
        System.out.println(dadosResult);


    }

    public DadosResult getDadosLivros() {
        System.out.println("Digite o nome do livro !");
        var nomeLivro = scanner.nextLine().toLowerCase().replace(" ", "%20");
        String json = consumo.obterDados(ENDERECO + nomeLivro);
        var dadosLivros = conversor.obterDados(json, DadosResult.class);
        return dadosLivros;
    }

}
