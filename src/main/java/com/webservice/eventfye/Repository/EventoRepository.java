package com.webservice.eventfye.Repository;

import com.webservice.eventfye.Model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    Evento findByIdEvento(Long id);


    @Query("SELECT e FROM Evento e WHERE e.nomeEvento = :nomeEvento AND e.dataInicioEvento = :dataInicioEvento AND e.dataFimEvento = :dataFimEvento AND (e.localEvento = :localEvento OR e.linkEvento = :linkEvento)")
    Optional<Evento> findByValues(
            @Param("nomeEvento") String nomeEvento,
            @Param("dataInicioEvento") ZonedDateTime dataInicioEvento,
            @Param("dataFimEvento") ZonedDateTime dataFimEvento,
            @Param("localEvento") String localEvento,
            @Param("linkEvento") String linkEvento);
}
