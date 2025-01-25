package com.webservice.eventfye.Controller;

import com.webservice.eventfye.Model.Palestrante;
import com.webservice.eventfye.Service.PalestranteService;
import com.webservice.eventfye.Dto.PalestranteDto;
import com.webservice.eventfye.Service.Exception.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/palestrante")
public class PalestranteController {

    private final PalestranteService palestranteService;

    public PalestranteController(PalestranteService palestranteService) {
        this.palestranteService = palestranteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Palestrante> buscarPalestrantePorId(@PathVariable Long id) {
        try {
            Palestrante palestrante = palestranteService.buscarPalestrantePorId(id);
            return ResponseEntity.ok(palestrante);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Optional<Palestrante>> buscarPalestrantePorNome(@PathVariable String nome) {
        Optional<Palestrante> palestrante = palestranteService.buscarPalestrantePorNome(nome);
        return palestrante.isPresent() ? ResponseEntity.ok(palestrante) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
    }

    @GetMapping("/palestrantes")
    public ResponseEntity<List<Palestrante>> buscarTodosPalestrantesOrdenadosPorNome() {
        List<Palestrante> palestrantes = palestranteService.buscarTodosPalestrantesOrdenadosPorNome();
        return ResponseEntity.ok(palestrantes);
    }

//    @PostMapping
//    public ResponseEntity<Palestrante> criarPalestrante(@RequestBody @Valid PalestranteDto palestranteDto) {
//        Palestrante palestrante = new Palestrante();
//        palestrante.setNomePalestrante(palestranteDto.nomePalestrante());
//        palestrante.setEmail(palestranteDto.email());
//        palestrante.setBioPalestrante(palestranteDto.bioPalestrante());
//        palestrante.setAreaExpertisePalestrante(palestranteDto.areaExpertisePalestrante());
//
//        Palestrante salvo = palestranteService.salvarPalestrante(palestrante);
//        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Palestrante> atualizarPalestrante(@PathVariable Long id, @RequestBody @Valid PalestranteDto palestranteDto) {
//        try {
//            Palestrante palestranteExistente = palestranteService.buscarPalestrantePorId(id);
//            palestranteExistente.setNomePalestrante(palestranteDto.nomePalestrante());
//            palestranteExistente.setEmail(palestranteDto.email());
//            palestranteExistente.setBioPalestrante(palestranteDto.bioPalestrante());
//            palestranteExistente.setAreaExpertisePalestrante(palestranteDto.areaExpertisePalestrante());
//            Palestrante atualizado = palestranteService.salvarPalestrante(palestranteExistente);
//            return ResponseEntity.ok(atualizado);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPalestrante(@PathVariable Long id) {
        try {
            palestranteService.excluirPalestrante(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
