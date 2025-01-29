package com.webservice.eventfye.Service;

import com.webservice.eventfye.Model.Certificado;
import com.webservice.eventfye.Model.Evento;
import com.webservice.eventfye.Model.Usuario;
import com.webservice.eventfye.Service.Utils.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class CertificadoService {

    @Autowired
    private PdfGenerator pdfGenerator;

    public Certificado gerarCertificado(String nomeUsuario, String nomeEvento, ZonedDateTime dataInicioEvento, ZonedDateTime dataFimEvento) {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(nomeUsuario);

        Evento evento = new Evento();
        evento.setNomeEvento(nomeEvento);
        evento.setDataInicioEvento(dataInicioEvento);
        evento.setDataFimEvento(dataFimEvento);

        Certificado certificado = Certificado.builder()
                .nomeUsuario(usuario)
                .nomeEvento(evento)
                .dataEmissaoCertificado(java.time.LocalDate.now())
                .codigoVerificacaoCertificado("codigo_exemplo")
                .build();
        try {
            pdfGenerator.gerarCertificadoPdf(certificado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certificado;
    }
}
