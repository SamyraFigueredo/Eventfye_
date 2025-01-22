package com.webservice.eventfye.Repository;

import com.webservice.eventfye.Model.Palestrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PalestranteRepository extends JpaRepository<Palestrante, Long> {

    Optional<Palestrante> findByNomeUsuario(String nome);

    List<Palestrante> findByAreaExpertisePalestrante(String areaExpertise);
    List<Palestrante> findByNomeUsuarioContaining(String nome);
    List<Palestrante> findByNomeUsuarioStartingWith(String nome);
    List<Palestrante> findAllByOrderByNomeUsuarioAsc();

    long countByAreaExpertisePalestrante(String areaExpertise);
}
