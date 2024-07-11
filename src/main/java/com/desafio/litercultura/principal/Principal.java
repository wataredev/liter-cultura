package com.desafio.litercultura.principal;

import com.desafio.litercultura.model.*;
import com.desafio.litercultura.repository.AutorRepository;
import com.desafio.litercultura.repository.LivroRepository;
import com.desafio.litercultura.service.ConsumoAPI;
import com.desafio.litercultura.service.ConverteDados;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private Livros livros = new Livros();

    private final LivroRepository livroRepositorio;
    private final AutorRepository autorRepository;

    Scanner scanner = new Scanner(System.in);

    private final String ENDERECO = "https://gutendex.com/books/?search=";

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
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                buscarLivro();
                break;
            case 2:
                listarLivrosListados();
                break;
            case 3:
                listarAutoresRegistrados();
                break;
            case 4:
                listarAutoresVivosEmUmAno();
                break;
            case 5:
                listarLivrosDeUmIdioma();
                break;
            case 0:
                System.out.println("Saindo !!!");
                escolha = 0;
                break;
            default:
                System.out.println("Digite uma opção válida!");
        }

        }
    }

    private void buscarLivro() {
        DadosResult dados = getDadosLivros();

        if (dados.results().isEmpty()) {

            System.out.println("""
                    *********************************
                    
                    LIVRO NÃO DISPONÍVEL / ENCONTRADO
                    
                    *********************************
                    """);
            return;
        }
        DadosLivros primeiroLivro = dados.results().get(0);

        exibirInformacoesLivro(primeiroLivro);

        Livros livro = new Livros(primeiroLivro);

        for (DadosAutores dadosAutor : primeiroLivro.autores()) {
            String nomeAutor = dadosAutor.nomeAutor();
            Autores autor = autorRepository.findByNomeAutor(nomeAutor)
                    .orElseGet(() -> new Autores(dadosAutor));
            livro.setAutor(autor);
            autor.getLivro().add(livro);
            autorRepository.save(autor);
        }

        livroRepositorio.save(livro);
    }



    private DadosResult getDadosLivros() {
        System.out.println("Digite o nome do livro !");
        var nomeLivro = scanner.nextLine().toLowerCase().replace(" ", "%20");
        var json = consumo.obterDados(ENDERECO + nomeLivro);
        DadosResult dados = conversor.obterDados(json, DadosResult.class);
        return dados;
    }

    private void exibirInformacoesLivro(DadosLivros livro) {
        System.out.println("----- LIVRO -----");
        System.out.println("Título: " + livro.titulo());
        System.out.println("Número de Downloads: " + livro.numDownloads());
        System.out.println("Idiomas: " + livro.idioma());
        System.out.println("Autores:");
        for (DadosAutores autor : livro.autores()) {
            System.out.println("- " + autor.nomeAutor());
        }
        System.out.println("----------------");
    }


    private void listarLivrosListados() {
        List<Livros> livros = livroRepositorio.findAll();
        if (!livros.isEmpty()) {
            livros.forEach(System.out::println);
        } else {
            System.out.println("""
                    ************************
                    
                    Nenhum livro registrado!
                    
                    ************************
                    """);
        }
    }

    private void listarAutoresRegistrados() {
        List<Autores> autores = autorRepository.findAll();
        if (!autores.isEmpty()) {
            autores.forEach(System.out::println);
        } else {
            System.out.println("""
                    ************************
                    
                    Nenhum autor registrado!
                    
                    ************************
                    """);
        }
    }

    private void listarAutoresVivosEmUmAno() {
        System.out.println("Insira o ano que deseja pesquisar!");
        int anoEscolhido = scanner.nextInt();
        List<Autores> autores = autorRepository.getAutoresByAnoNascimentoGreaterThan(anoEscolhido);
        if (!autores.isEmpty()) {
            autores.forEach(System.out::println);
        } else {
            System.out.println("""
                    ************************
                    
                    Nenhum autor disponível
                    
                    ************************
                    """);
        }

    }

    private void listarLivrosDeUmIdioma() {
        System.out.println("""
                *** Digite o idioma desejado! ***
                
                es- espanhol
                en- inglês
                fr- francês
                pt- português
                
                *********************************
                """);
        var idiomaEscolhido = scanner.nextLine();

        List<Livros> livros = livroRepositorio.getLivrosByIdioma(idiomaEscolhido);
        if (!livros.isEmpty()) {
            livros.forEach(System.out::println);
        } else {
            System.out.println("\nLivro não encontrado ou Idioma inválido");
        }

    }

}
