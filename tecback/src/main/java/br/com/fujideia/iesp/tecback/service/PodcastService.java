package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Podcast;
import br.com.fujideia.iesp.tecback.repository.PodcastRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class PodcastService {
    @Autowired
    private PodcastRepository repository;

    public Podcast salvar(Podcast podcast) {
        podcast = repository.save(podcast);
        return podcast;
    }

    public Podcast alterar(Podcast podcast) {
        if (Objects.nonNull(podcast.getId())) {
            podcast = repository.save(podcast);
        } else {
            throw new NotFoundException();
        }
        return podcast;
    }

    public List<Podcast> buscarTodos() {
        return repository.findAll();
    }

    public Boolean excluir(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            log.info("Erro ao realizar Exclusão: {}", e);
            return false;
        }
        return true;
    }

    public Podcast consultarPorId(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }
}
