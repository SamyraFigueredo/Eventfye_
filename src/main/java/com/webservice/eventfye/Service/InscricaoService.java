package com.webservice.eventfye.Service;

import com.webservice.eventfye.Model.Inscricao;
import com.webservice.eventfye.Repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    public Inscricao salvarInscricao(Inscricao inscricao) {
        inscricao.setDataInscricao(ZonedDateTime.now());
        return inscricaoRepository.save(inscricao);
    }

    public List<Inscricao> listarTodasInscricoes() {
        return inscricaoRepository.findAll();
    }

    public Optional<Inscricao> buscarInscricaoPorId(Long idInscricao) {
        return inscricaoRepository.findById(idInscricao);
    }

    public List<Inscricao> buscarInscricoesPorUsuario(UUID usuarioId) {
        return inscricaoRepository.findByUsuarioId(usuarioId);
    }

    public List<Inscricao> buscarInscricoesPorEvento(Long eventoId) {
        return inscricaoRepository.findByEventoIdEvento(eventoId);
    }

    public Inscricao atualizarStatusInscricao(Long idInscricao, String novoStatus) {
        Optional<Inscricao> inscricaoOptional = inscricaoRepository.findById(idInscricao);
        if (inscricaoOptional.isPresent()) {
            Inscricao inscricao = inscricaoOptional.get();
            inscricao.setStatusInscricao(novoStatus);
            return inscricaoRepository.save(inscricao);
        }
        throw new RuntimeException("Inscrição não encontrada com o ID: " + idInscricao);
    }

    public void excluirInscricao(Long idInscricao) {
        if (inscricaoRepository.existsById(idInscricao)) {
            inscricaoRepository.deleteById(idInscricao);
        } else {
            throw new RuntimeException("Inscrição não encontrada com o ID: " + idInscricao);
        }
    }
}
