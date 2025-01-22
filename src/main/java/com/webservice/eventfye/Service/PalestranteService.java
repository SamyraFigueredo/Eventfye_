package com.webservice.eventfye.Service;

import com.webservice.eventfye.Model.Palestrante;
import com.webservice.eventfye.Repository.PalestranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PalestranteService {

    @Autowired
    private PalestranteRepository palestranteRepository;

    public Optional<Palestrante> buscarPalestrantePorNome(String nome) {
        return palestranteRepository.findByNomeUsuario(nome);
    }

    public List<Palestrante> buscarPalestrantesPorAreaExpertise(String areaExpertise) {
        return palestranteRepository.findByAreaExpertisePalestrante(areaExpertise);
    }

    public List<Palestrante> buscarPalestrantesPorNomeContendo(String nome) {
        return palestranteRepository.findByNomeUsuarioContaining(nome);
    }

    public List<Palestrante> buscarPalestrantesPorNomeComecandoCom(String nome) {
        return palestranteRepository.findByNomeUsuarioStartingWith(nome);
    }

    public List<Palestrante> buscarTodosPalestrantesOrdenadosPorNome() {
        return palestranteRepository.findAllByOrderByNomeUsuarioAsc();
    }

    public long contarPalestrantesPorAreaExpertise(String areaExpertise) {
        return palestranteRepository.countByAreaExpertisePalestrante(areaExpertise);
    }

    public Palestrante salvarPalestrante(Palestrante palestrante) {
        return palestranteRepository.save(palestrante);
    }

    public void excluirPalestrante(Long id) {
        palestranteRepository.deleteById(id);
    }

    public void excluirPalestrantesPorAreaExpertise(String areaExpertise) {
        List<Palestrante> palestrantes = palestranteRepository.findByAreaExpertisePalestrante(areaExpertise);
        if (palestrantes.isEmpty()) {
            throw new RuntimeException("Nenhum palestrante encontrado com a área de expertise fornecida.");
        }
        palestranteRepository.deleteAll(palestrantes);
    }
}
