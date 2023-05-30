package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Cartao;
import br.com.fujideia.iesp.tecback.model.Usuario;
import br.com.fujideia.iesp.tecback.service.CartaoService;
import br.com.fujideia.iesp.tecback.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private CartaoService serviceCard;

    @PostMapping
    public ResponseEntity<Usuario> salvar(@Valid @RequestBody Usuario usuario){
        serviceCard.salvar(usuario.getCartao());
        usuario = service.cadastrar(usuario);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterar(@Valid @PathVariable("id") Integer id, @RequestBody Usuario usuario){
        service.consultarPorId(id);
        serviceCard.alterar(usuario.getCartao());
        usuario = service.alterar(usuario);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}/cartao")
    public ResponseEntity<Usuario> alterarCartao(@Valid @PathVariable("id") Integer id, @RequestBody Cartao cartao) {
        Usuario usuario = service.consultarPorId(id);

        usuario.setCartao(cartao);

        serviceCard.alterar(usuario.getCartao());
        usuario = service.alterar(usuario);

        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos(){
        return ResponseEntity.ok(service.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario>consultar(@Valid @PathVariable("id") Integer id){
        return ResponseEntity.ok(service.consultarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>excluir(@Valid @PathVariable("id") Integer id){
        if(service.excluir(id)){
            serviceCard.excluir(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
