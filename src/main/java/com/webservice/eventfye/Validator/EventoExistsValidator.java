package com.webservice.eventfye.Validator;

import com.webservice.eventfye.Repository.EventoRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EventoExistsValidator implements ConstraintValidator<EventoExists, Long> {

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public boolean isValid(Long idEvento, ConstraintValidatorContext context) {
        if (idEvento == null) {
            return false;
        }
        return eventoRepository.existsById(idEvento);
    }
}
