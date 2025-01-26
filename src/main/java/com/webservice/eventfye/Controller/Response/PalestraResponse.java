package com.webservice.eventfye.Controller.Response;

import com.webservice.eventfye.Model.Palestra;
import lombok.Getter;

@Getter
public class PalestraResponse {

    private final Long id;

    public PalestraResponse(Palestra palestra){
        id = palestra.getIdPalestra();
    }
}
