package com.webservice.eventfye.Validator;

import com.webservice.eventfye.Model.Evento;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.ZonedDateTime;

public class DataFimEventoValidador implements ConstraintValidator<DataFimEventoValida, Evento> {

    @Override
    public boolean isValid(Evento evento, ConstraintValidatorContext constraintValidatorContext) {
        ZonedDateTime dataInicial = evento.getDataInicioEvento();
        ZonedDateTime dataFinal = evento.getDataFimEvento();

        if (dataInicial == null || dataFinal == null) return false;

        return dataFinal.isAfter(dataInicial);
    }
}
