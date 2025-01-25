package com.webservice.eventfye.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PalestranteDto(
        @NotBlank(message = "O nome do palestrante é obrigatório.")
        String nomePalestrante,

        @NotBlank(message = "O email do palestrante é obrigatório.")
        @Email(message = "O email deve ser válido.")
        String email, String bioPalestrante, String areaExpertisePalestrante
) {}
