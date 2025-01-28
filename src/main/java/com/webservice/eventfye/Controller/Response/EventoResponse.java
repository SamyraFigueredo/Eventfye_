package com.webservice.eventfye.Controller.Response;

import com.webservice.eventfye.Model.Evento;
import com.webservice.eventfye.Model.Palestra;
import lombok.Getter;
import org.eclipse.angus.mail.util.BASE64EncoderStream;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Getter
public class EventoResponse {

    private final Long id;
    private String tituloEvento;
    private String descricaoEvento;
    private ZonedDateTime dataInicioEvento;
    private ZonedDateTime dataFimEvento;
    private String localEvento;
    private String iconeEvento;
    private String linkEvento;
    private List<Palestra> palestras;


    public EventoResponse(Long id) {
        this.id = id;
    }

    public static EventoResponse buildEventoResumido(Evento evento){
        EventoResponse eventoResponse = new EventoResponse(evento.getIdEvento());
        eventoResponse.tituloEvento = evento.getNomeEvento();
        eventoResponse.descricaoEvento = evento.getDescricaoEvento();
        eventoResponse.dataInicioEvento = evento.getDataInicioEvento();
        eventoResponse.dataFimEvento = evento.getDataFimEvento();
        return eventoResponse;
    }

    public EventoResponse(Evento evento) {
        this.id = evento.getIdEvento();
        this.descricaoEvento = evento.getDescricaoEvento();
        this.tituloEvento = evento.getNomeEvento();
        this.dataInicioEvento = evento.getDataInicioEvento();
        this.dataFimEvento = evento.getDataFimEvento();
        this.localEvento = evento.getLocalEvento();
        if (evento.getIconeEvento() != null) {
            this.iconeEvento = Base64.getEncoder().encodeToString(evento.getIconeEvento());
        } else{
            this.iconeEvento = null;
        }
        this.linkEvento = evento.getLinkEvento();
        this.palestras = new ArrayList<Palestra>();
        Optional.ofNullable(evento.getPalestras())
                .ifPresent(palestras::addAll);

    }


}
