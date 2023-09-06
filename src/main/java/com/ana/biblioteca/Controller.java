package com.ana.biblioteca;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class Controller{
    private List<Livro> livros;
    
    public Controller(){
        livros = new LinkedList<>();
        livros.add(new Livro(100,"Aprendendo Spring-Boot","Huguinho Pato",2020));
        livros.add(new Livro(120,"Aprendendo Java","Zezinho Pato",2015));
        livros.add(new Livro(140,"Aprendendo Outra coisa","Luizinho Pato",2023));
        livros.add(new Livro(140,"Aprendendo Uma coisa nova","Huguinho Pato",2023));
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String mensagemDeBemVindo() {
        return "Bem vindo a biblioteca central!";
    }

    @GetMapping("livros")
    @CrossOrigin(origins = "*")
    public List<Livro> getListaLivros(){
        return livros;
    }

    @GetMapping("autores")
    @CrossOrigin(origins = "*")
    public List<String> getListaAutores(){
        return livros.stream()
        .map(l->l.getAutor())
        .distinct()
        .toList();
    }

    @GetMapping("livrosAutores")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivrosAutores(@RequestParam(value="autor") String autor){
        return livros.stream()
            .filter(livro->livro.getAutor().equals(autor))
            .toList();
    }

    @GetMapping("livrosAutores/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivrosAutores(@PathVariable(value="autor") String autor, @PathVariable(value="ano") int ano){
        return livros.stream()
            .filter(livro->livro.getAutor().equals(autor))
            .filter(livro->livro.getAno() == ano)
            .toList();
    }

    @PostMapping("/novoLivro")
    @CrossOrigin(origins = "*")
    public boolean cadastraLivro(@RequestBody final Livro livro){
        livros.add(livro);
        return true;
    }

}