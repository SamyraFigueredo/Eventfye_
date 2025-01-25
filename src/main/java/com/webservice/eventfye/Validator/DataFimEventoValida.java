package com.webservice.eventfye.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DataFimEventoValidador.class)
@Target({ElementType.RECORD_COMPONENT, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataFimEventoValida {
    String message() default "Data de término inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
