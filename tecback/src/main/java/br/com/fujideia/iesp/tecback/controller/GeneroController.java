package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Genero;
import br.com.fujideia.iesp.tecback.service.GeneroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroController {
    @Autowired
    private GeneroService service;

    @PostMapping
    public ResponseEntity<Genero> salvar(@Valid @RequestBody Genero genero){
        genero = service.salvar(genero);
        return ResponseEntity.ok(genero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> alterar(@Valid @PathVariable("id") Integer id, @RequestBody Genero genero){
        service.consultarPorId(id);
        genero = service.alterar(genero);
        return ResponseEntity.ok(genero);
    }

    @GetMapping
    public ResponseEntity<List<Genero>> buscarTodos(){
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> consultar(@Valid @PathVariable("id") Integer id){
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
