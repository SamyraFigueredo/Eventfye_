package com.webservice.eventfye.Validator;

import com.webservice.eventfye.Model.Evento;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.ZonedDateTime;

public class DataInicioEventoValidador implements ConstraintValidator<DataInicioEventoValida, Evento> {

    @Override
    public boolean isValid(Evento evento, ConstraintValidatorContext constraintValidatorContext) {
        ZonedDateTime agora = ZonedDateTime.now();
        ZonedDateTime dataInicial = evento.getDataInicioEvento();
        ZonedDateTime dataFinal = evento.getDataFimEvento();

        if (dataInicial == null || dataFinal == null) return false;

        return dataInicial.isAfter(agora) && dataInicial.isBefore(dataFinal);
    }
}
