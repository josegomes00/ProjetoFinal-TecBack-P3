package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Cartao;
import br.com.fujideia.iesp.tecback.repository.CartaoRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repository;

    public Cartao salvar(Cartao cartao){
        cartao =  repository.save(cartao);
        return cartao;
    }

    public Cartao alterar(Cartao cartao){
        if(Objects.nonNull(cartao.getId())){
            cartao = repository.save(cartao);
        }else{
            throw new NotFoundException();
        }
        return cartao;
    }

    public Boolean excluir(Integer id){
        try {
            repository.deleteById(id);
        }catch (Exception e ){
            log.info("Erro ao realizar Exclusão : {}", e);
            return false;

        }
        return true;
    }
}
