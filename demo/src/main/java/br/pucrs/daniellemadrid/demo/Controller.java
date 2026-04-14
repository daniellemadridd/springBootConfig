package br.pucrs.daniellemadrid.demo;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/biblioteca")
public class Controller {
    private List<Livro> livros;
    private List<Livro> titulos;
    private List<Livro> autores;

    public Controller() {
        livros = new ArrayList<>(List.of(
                new Livro(1, "O hobbit", "J.R.R. Tolkien", 1937),
                new Livro(2, "1984", "George Orwell", 1949),
                new Livro(3, "Dom Quixote", "Miguel de Cervantes", 1605),
                new Livro(4, "exemplo", "Maria da Silva", 2026),
            new Livro(5, "Outro Exemplo", "Pedro da Silva", 2024)));

        autores = List.of(
                new Livro(1, "O hobbit", "J.R.R. Tolkien", 1937),
                new Livro(2, "1984", "George Orwell", 1949),
                new Livro(3, "Dom Quixote", "Miguel de Cervantes", 1605));

        titulos = List.of(
                new Livro(1, "O hobbit", "J.R.R. Tolkien", 1937),
                new Livro(2, "1984", "George Orwell", 1949),
                new Livro(3, "Dom Quixote", "Miguel de Cervantes", 1605));

    }

    @GetMapping("/")
    public String getMensagemInicial() {
        return "Aplicacao Spring-Boot funcionando!";
    }

    @GetMapping("/livros")
    public List<Livro> getLivros() {
        return livros;
    }

    @GetMapping("/autores")
    public List<Livro> getAutores() {
        return autores;
    }

    @GetMapping("/titulos")
    public List<Livro> getTitulos() {
        return titulos;
    }

    @GetMapping("/livrosautor")
    public List<Livro> getLivrosPorAutor(@RequestParam(value = "autor") String autor) {
        return livros.stream()
                .filter(livro -> livro.getAutor().equalsIgnoreCase(autor))
                .toList();
    }

    @GetMapping("/livrosautorano/{autor}/ano/{ano}")
    public List<Livro> getLivrosPorAutorEAno(@PathVariable(value = "autor") String autor,
            @PathVariable(value = "ano") int ano) {
        return livros.stream()
                .filter(livro -> livro.getAutor().equalsIgnoreCase(autor) && livro.getAno() == ano)
                .toList();
    }

    @PostMapping("/novolivro")
    public boolean getNovoLivro(@RequestBody final Livro novoLivro) {
        livros.add(novoLivro);
        return true;
    }
    
    @GetMapping("/livrotitulo/{titulo}")
    public ResponseEntity<Livro> getLivroPorTitulo(@PathVariable(value = "titulo") String titulo) {
        return livros.stream()
                .filter(livro -> livro.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}