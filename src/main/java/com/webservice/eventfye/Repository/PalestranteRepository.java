package com.webservice.eventfye.Repository;

import com.webservice.eventfye.Model.Palestrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PalestranteRepository extends JpaRepository<Palestrante, Long> {

    Optional<Palestrante> findByNomePalestrante(String nome);
    Optional<Palestrante> findByEmail(String email);
    List<Palestrante> findByAreaExpertisePalestrante(String areaExpertise);
    List<Palestrante> findByNomePalestranteContaining(String nome);
    List<Palestrante> findByNomePalestranteStartingWith(String nome);
    List<Palestrante> findAllByOrderByNomePalestranteAsc();


}
