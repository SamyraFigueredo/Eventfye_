package com.webservice.eventfye.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Programacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programacao")
    private Long idProgramacao;

    @Column(name = "nome_programacao", nullable = false, length = 100)
    private String nomeProgramacao;

    @Column(name = "data_hora_inicio_programacao", nullable = false)
    private LocalDateTime dataHoraInicioProgramacao;

    @Column(name = "data_hora_fim_programacao", nullable = false)
    private LocalDateTime dataHoraFimProgramacao;

    @Column(name = "descricao_programacao", length = 500)
    private String descricaoProgramacao;
}
