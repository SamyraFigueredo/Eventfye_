package com.webservice.eventfye.Controller.Response;

import com.webservice.eventfye.Model.Evento;
import lombok.Getter;

@Getter
public class EventoResponse {

    private final Long id;

    public EventoResponse(Evento evento) {
        id = evento.getIdEvento();
    }

}
