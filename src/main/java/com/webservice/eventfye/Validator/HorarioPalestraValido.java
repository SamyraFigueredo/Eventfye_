package com.webservice.eventfye.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = HorarioPalestraValidator.class)
@Target({  ElementType.TYPE  })
@Retention(RetentionPolicy.RUNTIME)
public @interface HorarioPalestraValido {
    String message() default "Horário inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
