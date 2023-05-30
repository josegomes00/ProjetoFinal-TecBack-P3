package br.com.fujideia.iesp.tecback.service;


import br.com.fujideia.iesp.tecback.model.Genero;
import br.com.fujideia.iesp.tecback.repository.GeneroRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class GeneroService {
    @Autowired
    private GeneroRepository repository;

    public Genero salvar(Genero genero) {
        if (Objects.nonNull(genero.getId())) {
            return genero;
        } else {
            genero = repository.save(genero);
            return genero;
        }
    }

    public Genero alterar(Genero genero){
        if (Objects.nonNull(genero.getId())){
            genero = repository.save(genero);
        }else{
            throw new NotFoundException();
        }
        return genero;
    }

    public List<Genero> buscarTodos(){
        return repository.findAll();
    }

    public Boolean excluir(Integer id){
        try{
            repository.deleteById(id);
        }catch(Exception e){
            log.info("Erro ao tentar excluir : {}", e);
            return false;
        }
        return true;
    }

    public Genero consultarPorId(Integer id){
        return repository
                .findById(id)
                .orElseThrow(NotFoundException::new);
    }


}
