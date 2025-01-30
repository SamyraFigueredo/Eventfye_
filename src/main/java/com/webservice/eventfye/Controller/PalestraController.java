package com.webservice.eventfye.Controller;

import com.webservice.eventfye.Controller.Request.PalestraRequest;
import com.webservice.eventfye.Controller.Response.PalestraResponse;
import com.webservice.eventfye.Model.Evento;
import com.webservice.eventfye.Model.Palestra;
import com.webservice.eventfye.Model.Palestrante;
import com.webservice.eventfye.Service.EventoService;
import com.webservice.eventfye.Service.Exception.EntityNotFoundException;
import com.webservice.eventfye.Service.PalestraService;
import com.webservice.eventfye.Service.PalestranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/palestras")
public class PalestraController {

    @Autowired
    PalestraService palestraService;

    @Autowired
    PalestranteService palestranteService;

    @Autowired
    EventoService eventoService;

    @PostMapping
    public ResponseEntity<PalestraResponse> create(@Valid @RequestBody PalestraRequest palestraRequest, @AuthenticationPrincipal Jwt principal){
        Evento evento = eventoService.findById(palestraRequest.idEvento());
        String idUsuario = principal.getClaimAsString("sub");
        if(evento.getIdUsuario().equals(idUsuario)) {
            Palestrante palestrante = new Palestrante();
            palestrante.setNomePalestrante(palestraRequest.nomePalestrante());
            palestrante.setAreaExpertisePalestrante(palestraRequest.expertisePalestrante());

            Palestra palestra = palestraService.insert(palestraRequest.toModel(palestranteService.salvarPalestrante(palestrante), evento));
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(palestra.getIdPalestra()).toUri();
            return ResponseEntity.created(uri).body(new PalestraResponse(palestra));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPalestra(@PathVariable Long id) {
        try {
            palestraService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
