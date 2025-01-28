package com.webservice.eventfye.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name = "usuario")
@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nome_usuario", nullable = false, length = 100)
    private String nomeUsuario;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "email_usuario", nullable = false, length = 100, unique = true)
    private String emailUsuario;
}
