package com.ana.biblioteca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/biblioteca")
public class Controller{
    private Acervo acervo;

    @Autowired
    public Controller(Acervo acervo){
        this.acervo = acervo;
    }

    @GetMapping("/")
    public String mensagemDeBemVindo() {
        return "Bem vindo a biblioteca central!";
    }

    @GetMapping("/livros")
    public List<Livro> getListaLivros(){
        return acervo.getAll();
    }

    @GetMapping("/autores")
    public List<String> getListaAutores(){
        return acervo.getAutores();
    }

    @GetMapping("/livrosAutores")
    public List<Livro> getLivrosAutores(@RequestParam(value="autor") String autor){
        return acervo.getLivrosAutores(autor);
    }

    @GetMapping("livrosAutores/{autor}/ano/{ano}")
    public List<Livro> getLivrosAutores(@PathVariable(value="autor") String autor, @PathVariable(value="ano") int ano){
        return acervo.getLivrosAutores(autor, ano);
    }

    @PostMapping("/novoLivro")
    public String cadastraLivro(@RequestBody final Livro livro){
        if(acervo.cadastraLivro(livro) == true)
            return "Livro cadastrado";
        return "Não foi possível cadastrar o livro :(";
    }

}