package com.webservice.eventfye.Repository;

import com.webservice.eventfye.Model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    List<Inscricao> findByUsuarioId(UUID usuarioId);
    List<Inscricao> findByEventoIdEvento(Long eventoId);
    List<Inscricao> findByStatusInscricao(String statusInscricao);
}
