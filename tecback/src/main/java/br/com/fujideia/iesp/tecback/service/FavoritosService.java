package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Favoritos;
import br.com.fujideia.iesp.tecback.repository.FavoritosRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FavoritosService {
    private final FavoritosRepository repository;

    @Autowired
    public FavoritosService(FavoritosRepository repository) {
        this.repository = repository;
    }

    public Favoritos cadastrar(Favoritos favoritos) {
        return repository.save(favoritos);
    }

    public Favoritos alterar(Favoritos favoritos) {
        if (favoritos.getId() != 0) {
            return repository.save(favoritos);
        } else {
            throw new NotFoundException();
        }
    }

    public void excluir(Integer id) {
        repository.deleteById(id);
    }

    public Favoritos consultarPorId(Integer id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }
}
