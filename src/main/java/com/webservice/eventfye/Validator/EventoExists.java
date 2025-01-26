package com.webservice.eventfye.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Constraint(validatedBy = EventoExistsValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface EventoExists {
    String message() default "Evento não encontrado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
