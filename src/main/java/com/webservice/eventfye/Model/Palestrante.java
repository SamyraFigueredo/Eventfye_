package com.webservice.eventfye.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Palestrante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_palestrante;

    @Column(name = "nome", nullable = false, length = 100)
    private String nomePalestrante;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "bio_palestrante", length = 500)
    private String bioPalestrante;

    @Column(name = "area_expertise_palestrante", length = 200)
    private String areaExpertisePalestrante;
}
