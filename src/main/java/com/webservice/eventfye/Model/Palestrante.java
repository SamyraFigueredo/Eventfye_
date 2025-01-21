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
public class Palestrante extends Usuario {

    @Column(name = "bio_palestrante", length = 500)
    private String bioPalestrante;

    @Column(name = "area_expertise_palestrante", length = 200)
    private String areaExpertisePalestrante;
}
