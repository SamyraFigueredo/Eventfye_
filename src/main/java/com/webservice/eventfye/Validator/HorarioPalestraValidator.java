package com.webservice.eventfye.Validator;

import com.webservice.eventfye.Controller.Request.PalestraRequest;
import com.webservice.eventfye.Model.Evento;
import com.webservice.eventfye.Repository.EventoRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Optional;

public class HorarioPalestraValidator implements ConstraintValidator <HorarioPalestraValido, PalestraRequest> {

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public boolean isValid(PalestraRequest palestraRequest, ConstraintValidatorContext context) {

        if (palestraRequest == null || palestraRequest.idEvento() == null || palestraRequest.horario() == null) {
            return false;
        }
        Optional<Evento> optionalEvento = eventoRepository.findById(palestraRequest.idEvento());
        if (optionalEvento.isEmpty()) {
            return false;
        }
        Evento evento = eventoRepository.findByIdEvento(palestraRequest.idEvento());
        ZonedDateTime eventoInicio = evento.getDataInicioEvento();
        ZonedDateTime eventoFim = evento.getDataFimEvento();

        LocalTime eventoStartTime = eventoInicio.toLocalTime();
        LocalTime eventoEndTime = eventoFim.toLocalTime();
        LocalTime horario = palestraRequest.horario();

        return !horario.isBefore(eventoStartTime) && !horario.isAfter(eventoEndTime);

    }

}
