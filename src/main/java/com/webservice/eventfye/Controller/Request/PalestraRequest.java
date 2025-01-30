package com.webservice.eventfye.Controller.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webservice.eventfye.Model.Evento;
import com.webservice.eventfye.Model.Palestra;
import com.webservice.eventfye.Model.Palestrante;
import com.webservice.eventfye.Validator.EventoExists;
import com.webservice.eventfye.Validator.HorarioPalestraValido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

public record PalestraRequest (
        @NotNull(message = "O nome não pode ser nulo")
        @NotBlank(message = "O nome não pode ficar em branco")
        @Size(min = 5, max = 100, message = "O nome precisa ter entre 5 e 100 caracteres")
        String nome,
        @NotNull(message = "O horário não pode ser nulo")
        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime horario,
        @NotNull(message = "O nome do Palestrante não pode ser nulo")
        @NotBlank(message = "O nome do Palestrante não pode ficar em branco")
        @Size(min = 5, max = 100, message = "O nome do Palestrante precisa ter entre 5 e 100 caracteres")
        String nomePalestrante,
        @NotNull(message = "A expertise do Palestrante não pode ser nula")
        @NotBlank(message = "A expertise do Palestrante não pode ficar em branco")
        @Size(min = 5, max = 100, message = "O nome da expertise precisa ter entre 5 e 100 caracteres")
        String expertisePalestrante,
        @NotNull(message = "O id do evento não pode ser nulo")
        @EventoExists
        Long idEvento
        ){

    public Palestra toModel(Palestrante palestrante, Evento evento) {
        Palestra palestra = new Palestra();
        palestra.setNomePalestra(nome);
        palestra.setHorarioPalestra(horario);
        palestra.setPalestrante(palestrante);
        palestra.setEvento(evento);
        return palestra;
    }
}
