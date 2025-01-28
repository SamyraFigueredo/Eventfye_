package com.webservice.eventfye.Controller;

import com.webservice.eventfye.Controller.Request.EventoRequest;
import com.webservice.eventfye.Controller.Response.EventoResponse;
import com.webservice.eventfye.Model.Evento;
import com.webservice.eventfye.Service.EventoService;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/eventos")
public class EventoController {


    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<EventoResponse> create(@Valid @RequestBody EventoRequest eventoRequest, @RequestParam("file") MultipartFile file, @AuthenticationPrincipal Jwt principal) {
        Evento evento = eventoService.insert(eventoRequest.toModel(file), principal.getClaimAsString("sub"));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(evento.getIdEvento()).toUri();
        return ResponseEntity.created(uri).body(new EventoResponse(evento.getIdEvento()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponse> getEvento(@Valid @PathVariable Long id, @AuthenticationPrincipal Jwt principal){
        String idUsuario = principal.getClaimAsString("sub");
        if (idUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Evento> evento = Optional.ofNullable(eventoService.findById(id));
        if(evento.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(new EventoResponse(evento.get()));
    }

    @GetMapping
    public ResponseEntity<List<EventoResponse>> getEventos(@AuthenticationPrincipal Jwt principal){
        String idUsuario = principal.getClaimAsString("sub");
        if (idUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok().body(
                eventoService.findAll()
                .stream()
                .map(EventoResponse::buildEventoResumido)
                .toList()
        );
    }

}
