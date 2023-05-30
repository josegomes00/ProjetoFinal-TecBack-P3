package br.com.fujideia.iesp.tecback.controller;


import br.com.fujideia.iesp.tecback.model.Serie;
import br.com.fujideia.iesp.tecback.service.GeneroService;
import br.com.fujideia.iesp.tecback.service.SerieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serie")
public class SerieController {

    @Autowired
    private SerieService service;

    @Autowired
    private GeneroService generoService;

    @PostMapping
    public ResponseEntity<Serie> salvar(@Valid @RequestBody Serie serie){
        generoService.salvar(serie.getGenero());
        serie = service.salvar(serie);
        return ResponseEntity.ok(serie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Serie> alterar(@Valid @PathVariable("id") Integer id, @RequestBody Serie serie){
        service.consultarPorId(id);
        generoService.alterar(serie.getGenero());
        serie = service.alterar(serie);
        return ResponseEntity.ok(serie);
    }

    @GetMapping
    public ResponseEntity<List<Serie>>buscarTodas(){
        return ResponseEntity.ok(service.buscarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Serie>consultar(@Valid @PathVariable("id") Integer id){
        return ResponseEntity.ok(service.consultarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Serie>excluir(@Valid @PathVariable("id") Integer id){
        if(service.excluir(id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
