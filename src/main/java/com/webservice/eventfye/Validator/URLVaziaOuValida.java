package com.webservice.eventfye.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = URLValidador.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface URLVaziaOuValida
 {
    String message() default "URL inválida!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
