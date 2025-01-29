package com.webservice.eventfye.Repository;

import com.webservice.eventfye.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNomeUsuario(String nome);
    List<Usuario> findAllByOrderByNomeUsuarioAsc();
    Optional<Usuario> findByEmailUsuario(String email);
}
