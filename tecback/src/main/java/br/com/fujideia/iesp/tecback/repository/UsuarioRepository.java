package br.com.fujideia.iesp.tecback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<br.com.fujideia.iesp.tecback.model.Usuario, Integer> {
}
