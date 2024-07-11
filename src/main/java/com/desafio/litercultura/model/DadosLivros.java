package com.desafio.litercultura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(@JsonAlias("title") String titulo,
                          @JsonAlias("download_count") Integer numDownloads,
                          @JsonAlias("authors") List<DadosAutores> autores,
                          @JsonAlias("languages") List<String> idioma) {

}
