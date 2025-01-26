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

    @ElementCollection
    @CollectionTable(
            name = "palestrantes_palestra",
            joinColumns = @JoinColumn(name = "id_palestra")
    )
    @Column(name = "palestrante")
    private List<String> nomePalestrante;

    @Column(name = "horario_palestra")
    private LocalTime horarioPalestra;

}
