package com.webservice.eventfye.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.ZonedDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "evento")
@Entity
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long idEvento;

    @Column(name = "nome_evento")
    private String nomeEvento;

    @Column(name = "data_inicio_evento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime dataInicioEvento;
    @Column(name = "data_fim_evento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime dataFimEvento;

    @Column(name = "desc_evento")
    private String descricaoEvento;

    @Column(name = "link_evento")
    private String linkEvento;

    @Lob
    @Column(name = "icone_evento")
    private byte[] iconeEvento;

}
