package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Podcast;
import br.com.fujideia.iesp.tecback.service.PodcastService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/podcast")
public class PodcastController {

    @Autowired
    private PodcastService service;

    @PostMapping
    public ResponseEntity<Podcast> salvar(@Valid @RequestBody Podcast podcast) {
        podcast = service.salvar(podcast);
        return ResponseEntity.ok(podcast);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Podcast> alterar(@Valid @PathVariable("id") Integer id, @RequestBody Podcast podcast) {
        Podcast podcastExistente = service.consultarPorId(id);
        podcast.setId(podcastExistente.getId()); // Define o ID do podcast existente no objeto a ser alterado
        podcast = service.alterar(podcast);
        return ResponseEntity.ok(podcast);
    }

    @GetMapping
    public ResponseEntity<List<Podcast>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Podcast> consultar(@Valid @PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.consultarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> excluir(@Valid @PathVariable("id") Integer id) {
        if (service.excluir(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
