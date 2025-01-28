package com.webservice.eventfye.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Base64;

public class ImagemBase64Validator implements ConstraintValidator<ImagemBase64Valida, String> {

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        if (valor == null || valor.isEmpty()) {
            return true;
        }

        try {
            byte[] bytes = Base64.getDecoder().decode(valor);
            String tipoMIME = java.net.URLConnection.guessContentTypeFromStream(new java.io.ByteArrayInputStream(bytes));
            return tipoMIME != null && tipoMIME.startsWith("image/");
        } catch (IllegalArgumentException | java.io.IOException e) {
            return false;
        }
    }
}
