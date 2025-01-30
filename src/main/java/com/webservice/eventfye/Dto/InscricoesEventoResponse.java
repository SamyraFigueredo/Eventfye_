package com.webservice.eventfye.Dto;

import java.time.ZonedDateTime;
import java.util.List;

public record InscricoesEventoResponse(Long idInscricao, UserDTO participante, String statusInscricao, ZonedDateTime dataInscricao) {
}
