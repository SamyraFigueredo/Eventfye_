package com.webservice.eventfye.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ImagemBase64Validator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImagemBase64Valida {
    String message() default "Imagem codificada em Base64 inválida.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
