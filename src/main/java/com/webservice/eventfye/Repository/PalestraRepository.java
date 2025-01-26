package com.webservice.eventfye.Repository;

import com.webservice.eventfye.Model.Palestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface PalestraRepository extends JpaRepository<Palestra,Long> {

    @Query("SELECT e FROM Palestra e WHERE e.nomePalestra = :nomePalestra AND e.horarioPalestra = :horarioPalestra AND e.nomePalestrante IN :nomePalestrante")
    Optional<Palestra> findByValues(
            @Param("nomePalestra") String nomePalestra,
            @Param("nomePalestrante") List<String> nomePalestrante,
            @Param("horarioPalestra") LocalTime horarioPalestra);
}
