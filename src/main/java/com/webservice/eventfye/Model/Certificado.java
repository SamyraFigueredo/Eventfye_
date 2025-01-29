package com.webservice.eventfye.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCertificado;

    @Column(name = "data_emissao_certificado", nullable = false)
    private LocalDate dataEmissaoCertificado;

    @Column(name = "codigo_verificacao_certificado", nullable = false, unique = true)
    private String codigoVerificacaoCertificado;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario nomeUsuario;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento nomeEvento;

    @Column(name = "data_inicio_evento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private ZonedDateTime dataInicioEvento;

    @Column(name = "data_fim_evento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    private ZonedDateTime dataFimEvento;

    @Column(name = "caminho_arquivo_certificado", nullable = true)
    private String caminhoArquivoCertificado;
}
