package com.ana.biblioteca;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Acervo {
    private List<Livro> livros;

    public Acervo(){
        livros = new LinkedList<>();

        livros.add(new Livro(100,"Aprendendo Spring-Boot","Huguinho Pato",2020));
        livros.add(new Livro(120,"Aprendendo Java","Zezinho Pato",2015));
        livros.add(new Livro(140,"Aprendendo Outra coisa","Luizinho Pato",2023));
        livros.add(new Livro(140,"Aprendendo Uma coisa nova","Huguinho Pato",2023));
    }

    public List<Livro> getAll(){
        return livros;
    }

    public List<String> getAutores(){
        return livros.stream()
        .map(l->l.getAutor())
        .distinct()
        .toList();
    }
    
    public List<Livro> getLivrosAutores(String autor){
        return livros.stream()
            .filter(livro->livro.getAutor().equals(autor))
            .toList();
    }

    public List<Livro> getLivrosAutores(String autor, int ano){
        return livros.stream()
            .filter(livro->livro.getAutor().equals(autor))
            .filter(livro->livro.getAno() == ano)
            .toList();
    }

    public boolean cadastraLivro(Livro livro){
        livros.add(livro);
        return true;
    }
}