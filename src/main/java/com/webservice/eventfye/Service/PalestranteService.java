package com.webservice.eventfye.Service;

import com.webservice.eventfye.Model.Palestrante;
import com.webservice.eventfye.Repository.PalestranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PalestranteService {

    @Autowired
    private PalestranteRepository palestranteRepository;

    public Optional<Palestrante> buscarPalestrantePorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do palestrante não pode ser nulo ou vazio.");
        }
        return palestranteRepository.findByNomePalestrante(nome);
    }

    public Palestrante buscarPalestrantePorId(Long id) {
        return palestranteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Palestrante com ID " + id + " não encontrado."));
    }

    public List<Palestrante> buscarPalestrantesPorAreaExpertise(String areaExpertise) {
        if (areaExpertise == null || areaExpertise.isBlank()) {
            throw new IllegalArgumentException("A área de expertise não pode ser nula ou vazia.");
        }
        return palestranteRepository.findByAreaExpertisePalestrante(areaExpertise);
    }

    public List<Palestrante> buscarPalestrantesPorNomeContendo(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        return palestranteRepository.findByNomePalestranteContaining(nome);
    }

    public List<Palestrante> buscarPalestrantesPorNomeComecandoCom(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        return palestranteRepository.findByNomePalestranteStartingWith(nome);
    }

    public List<Palestrante> buscarTodosPalestrantesOrdenadosPorNome() {
        return palestranteRepository.findAllByOrderByNomePalestranteAsc();
    }

    public Palestrante salvarPalestrante(Palestrante palestrante) {
        if (palestrante == null) {
            throw new IllegalArgumentException("O palestrante não pode ser nulo.");
        }
        return palestranteRepository.save(palestrante);
    }

    public void excluirPalestrante(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID do palestrante não pode ser nulo.");
        }
        if (!palestranteRepository.existsById(id)) {
            throw new EntityNotFoundException("Nenhum palestrante encontrado com o ID fornecido.");
        }
        palestranteRepository.deleteById(id);
    }
}
