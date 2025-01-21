package com.webservice.eventfye.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Presenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presenca")
    private Long idPresenca;

    @Column(name = "data_hora_presenca", nullable = false)
    private LocalDateTime dataHoraPresenca;

    @Column(name = "status_presenca", nullable = false, length = 20)
    private String statusPresenca;
}
