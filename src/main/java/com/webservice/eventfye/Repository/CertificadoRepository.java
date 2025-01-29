package com.webservice.eventfye.Repository;

import com.webservice.eventfye.Model.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CertificadoRepository extends JpaRepository<Certificado, Long> {
}
