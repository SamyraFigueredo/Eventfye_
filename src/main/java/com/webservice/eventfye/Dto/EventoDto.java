package com.webservice.eventfye.Dto;

import jakarta.validation.constraints.FutureOrPresent;
import org.springframework.web.multipart.MultipartFile;

public record EventoDto(String nomeEvento, String descricaoEvento, String linkEvento, @FutureOrPresent String dataInicioEvento, String horarioInicioEvento, @FutureOrPresent String dataFimEvento, String horarioFimEvento, String localEvento, MultipartFile iconeEvento) {
}
