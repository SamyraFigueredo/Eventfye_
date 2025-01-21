package com.webservice.eventfye.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscricao")
    private Long idInscricao;

    @Column(name = "status_inscricao", nullable = false, length = 20)
    private String statusInscricao;

    @Column(name = "data_inscricao", nullable = false)
    private LocalDate dataInscricao;
}
