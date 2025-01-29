package com.webservice.eventfye.Controller;

import com.webservice.eventfye.Model.Certificado;
import com.webservice.eventfye.Service.CertificadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/certificado")
public class CertificadoController {

    @Autowired
    private CertificadoService certificadoService;

    @PostMapping("/gerar")
    public Certificado gerarCertificado(@RequestBody CertificadoRequest request) {
        return certificadoService.gerarCertificado(
                request.getNomeUsuario(),
                request.getNomeEvento(),
                request.getDataInicioEvento(),
                request.getDataFimEvento()
        );
    }

    public static class CertificadoRequest {
        private String nomeUsuario;
        private String nomeEvento;
        private ZonedDateTime dataInicioEvento;
        private ZonedDateTime dataFimEvento;

        public String getNomeUsuario() {
            return nomeUsuario;
        }

        public void setNomeUsuario(String nomeUsuario) {
            this.nomeUsuario = nomeUsuario;
        }

        public String getNomeEvento() {
            return nomeEvento;
        }

        public void setNomeEvento(String nomeEvento) {
            this.nomeEvento = nomeEvento;
        }

        public ZonedDateTime getDataInicioEvento() {
            return dataInicioEvento;
        }

        public void setDataInicioEvento(ZonedDateTime dataInicioEvento) {
            this.dataInicioEvento = dataInicioEvento;
        }

        public ZonedDateTime getDataFimEvento() {
            return dataFimEvento;
        }

        public void setDataFimEvento(ZonedDateTime dataFimEvento) {
            this.dataFimEvento = dataFimEvento;
        }
    }
}
