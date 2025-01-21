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
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCertificado;

    @Column(name = "data_emissao_certificado", nullable = false)
    private LocalDate dataEmissaoCertificado;

    @Column(name = "codigo_verificacao_certificado", nullable = false, unique = true)
    private String codigoVerificacaoCertificado;
}
