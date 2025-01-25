package com.webservice.eventfye.Repository;

import com.webservice.eventfye.Model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    List<Inscricao> findByUsuarioId(Long usuarioId);
    List<Inscricao> findByEventoId(Long eventoId);
    List<Inscricao> findByStatusInscricao(String statusInscricao);
}
