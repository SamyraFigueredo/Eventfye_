package com.webservice.eventfye.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "palestrante")
public class Palestrante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_palestrante")
    private Long idPalestrante;

    @Column(name = "nome", nullable = false, length = 100)
    private String nomePalestrante;

    @Column(name = "bio_palestrante", length = 500)
    @Nullable
    private String bioPalestrante;

    @Column(name = "area_expertise_palestrante", length = 200)
    private String areaExpertisePalestrante;

    @OneToMany(mappedBy = "palestrante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Palestra> palestras = new ArrayList<>();
}
