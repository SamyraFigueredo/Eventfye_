package com.webservice.eventfye.Controller.Request;

import com.webservice.eventfye.Model.Evento;
import com.webservice.eventfye.Validator.DataFimEventoValida;
import com.webservice.eventfye.Validator.DataInicioEventoValida;
import com.webservice.eventfye.Validator.URLVaziaOuValida;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.ZonedDateTime;
import java.util.Base64;

public record EventoRequest(
        @NotNull(message = "O nome não pode ficar em branco")
        @Size(min = 3, max = 100, message = "O nome precisa ter entre 3 e 100 caracteres")
        String tituloEvento,
        @NotNull(message = "A descrição do evento não pode ficar em branco")
        @Size(max = 1500, message = "A descrição precisa ser menor que 1500 caracteres")
        String descricaoEvento,
        @FutureOrPresent(message = "A data inicial precisa ser atual ou futura")
        @NotNull(message = "A data inicial não pode ficar em branco")
        @DataInicioEventoValida
        ZonedDateTime dataInicioEvento,
        @DataFimEventoValida
        @NotNull(message = "A data final não pode ficar em branco")
        ZonedDateTime dataFimEvento,
        @NotNull(message = "O local do evento não pode ficar em branco")
        @Size(min = 15, max = 255, message = "O endereço precisa ter entre 15 e 255 caracteres")
        String localEvento,
        String iconeEvento,
        @URLVaziaOuValida
        String linkEvento
        ){

    public Evento toModel(){
        Evento evento = new Evento();
        evento.setNomeEvento(tituloEvento);
        evento.setDescricaoEvento(descricaoEvento);
        if(linkEvento.isEmpty()) {
            evento.setLinkEvento(null);
        } else {
            evento.setLinkEvento(linkEvento);
        }
        evento.setDataInicioEvento(dataInicioEvento);
        evento.setDataFimEvento(dataFimEvento);
        evento.setLocalEvento(localEvento);
        try {
            if (iconeEvento != null && !iconeEvento.isEmpty()) {
                byte[] bytesDaImagem = Base64.getDecoder().decode(iconeEvento);
                evento.setIconeEvento(bytesDaImagem);
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Falha ao decodificar o ícone do Evento (Base64 inválido)", e);
        }

        return evento;
    }

}
