package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Favoritos;
import br.com.fujideia.iesp.tecback.model.Usuario;
import br.com.fujideia.iesp.tecback.service.FavoritosService;
import br.com.fujideia.iesp.tecback.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario/{usuarioId}/favoritos")
public class FavoritosController {

    private final FavoritosService favoritosService;
    private final UsuarioService usuarioService;

    @Autowired
    public FavoritosController(FavoritosService favoritosService, UsuarioService usuarioService) {
        this.favoritosService = favoritosService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Favoritos> cadastrarFavorito(
            @PathVariable("usuarioId") Integer usuarioId,
            @RequestBody Favoritos favoritos
    ) {
        Usuario usuario = usuarioService.consultarPorId(usuarioId);
        favoritos = favoritosService.cadastrar(favoritos);
        usuario.getFavoritos().add(favoritos);
        usuarioService.alterar(usuario);
        return ResponseEntity.ok(favoritos);
    }

    @PutMapping("/{favoritoId}")
    public ResponseEntity<Favoritos> alterarFavorito(
            @PathVariable("usuarioId") Integer usuarioId,
            @PathVariable("favoritoId") Integer favoritoId,
            @RequestBody Favoritos favoritos
    ) {
        Usuario usuario = usuarioService.consultarPorId(usuarioId);
        Favoritos favoritosExistente = favoritosService.consultarPorId(favoritoId);

        if (usuario.getFavoritos().contains(favoritosExistente)) {
            favoritos.setId(favoritoId);
            favoritos = favoritosService.alterar(favoritos);
            return ResponseEntity.ok(favoritos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{favoritoId}")
    public ResponseEntity<Void> excluirFavorito(
            @PathVariable("usuarioId") Integer usuarioId,
            @PathVariable("favoritoId") Integer favoritoId
    ) {
        Usuario usuario = usuarioService.consultarPorId(usuarioId);
        Favoritos favoritos = favoritosService.consultarPorId(favoritoId);

        if (usuario.getFavoritos().contains(favoritos)) {
            usuario.getFavoritos().remove(favoritos);
            usuarioService.alterar(usuario);
            favoritosService.excluir(favoritoId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Favoritos>> listarFavoritosUsuario(@PathVariable("usuarioId") Integer usuarioId) {
        Usuario usuario = usuarioService.consultarPorId(usuarioId);
        List<Favoritos> favoritos = usuario.getFavoritos();
        return ResponseEntity.ok(favoritos);
    }
}
