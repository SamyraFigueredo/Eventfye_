package com.webservice.eventfye.Repository;

import com.webservice.eventfye.Model.Evento;
import com.webservice.eventfye.Model.Palestra;
import com.webservice.eventfye.Model.Palestrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.Optional;

public interface PalestraRepository extends JpaRepository<Palestra,Long> {

    @Query("SELECT e FROM Palestra e WHERE e.nomePalestra = :nomePalestra AND e.horarioPalestra = :horarioPalestra AND e.palestrante = :palestrante AND e.evento = :evento")
    Optional<Palestra> findByValues(
            @Param("nomePalestra") String nomePalestra,
            @Param("palestrante") Palestrante palestrante,
            @Param("evento") Evento evento,
            @Param("horarioPalestra") LocalTime horarioPalestra);


}
