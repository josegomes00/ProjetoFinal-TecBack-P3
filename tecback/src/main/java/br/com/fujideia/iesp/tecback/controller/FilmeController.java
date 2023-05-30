package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.service.FilmeService;
import br.com.fujideia.iesp.tecback.service.GeneroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    @Autowired
    private FilmeService service;

    @Autowired
    private GeneroService generoService;

    @PostMapping
    public ResponseEntity<Filme> salvar(@Valid @RequestBody Filme filme){
        generoService.salvar(filme.getGenero());
        filme = service.salvar(filme);
        return ResponseEntity.ok(filme);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> alterar(@Valid @PathVariable("id") Integer id, @RequestBody Filme filme){
        Filme filmeExistente = service.consultarPorId(id);
        generoService.alterar(filme.getGenero());
        filme.setId(filmeExistente.getId()); // Define o ID do filme existente no objeto a ser alterado
        filme = service.alterar(filme);
        return ResponseEntity.ok(filme);
    }

    @GetMapping
    public ResponseEntity<List<Filme>> buscarTodos(){
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> consultar(@Valid @PathVariable("id") Integer id){
        return ResponseEntity.ok(service.consultarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> excluir(@Valid @PathVariable("id") Integer id){
        if(service.excluir(id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
