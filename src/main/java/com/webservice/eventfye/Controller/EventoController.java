package com.webservice.eventfye.Controller;

import com.webservice.eventfye.Controller.Request.EventoRequest;
import com.webservice.eventfye.Controller.Response.EventoResponse;
import com.webservice.eventfye.Model.Evento;
import com.webservice.eventfye.Service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/evento")
public class EventoController {

    @Autowired
    EventoService eventoService;

    @PostMapping
    public ResponseEntity<EventoResponse> create(@Valid @RequestBody EventoRequest eventoRequest) {
        Evento evento = eventoService.insert(eventoRequest.toModel());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(evento.getIdEvento()).toUri();
        return ResponseEntity.created(uri).body(new EventoResponse(evento));
    }

}
