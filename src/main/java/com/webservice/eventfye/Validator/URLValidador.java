package com.webservice.eventfye.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.MalformedURLException;

public class URLValidador implements ConstraintValidator<URLVaziaOuValida, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }
        try {
            URI uri = new URI(value);
            uri.toURL();
            return true;
        } catch (URISyntaxException | IllegalArgumentException | MalformedURLException e) {
            return false;
        }
    }
}
