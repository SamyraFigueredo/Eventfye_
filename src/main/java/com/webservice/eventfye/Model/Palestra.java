package com.webservice.eventfye.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "palestra")
public class Palestra implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_palestra")
    private Long idPalestra;

    @Column(name = "nome_palestra")
    private String nomePalestra;

    @Column(name = "horario_palestra")
    private LocalTime horarioPalestra;

    @ManyToOne
    @JoinColumn(name = "id_palestrante")
    private Palestrante palestrante;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;



}
