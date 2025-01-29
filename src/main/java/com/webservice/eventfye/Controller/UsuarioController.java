package com.webservice.eventfye.Controller;

import com.webservice.eventfye.Model.Usuario;
import com.webservice.eventfye.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participante")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> criarParticipante(@RequestBody Usuario usuario) {
        try {
            usuarioService.salvarUsuario(usuario);
            return new ResponseEntity<>("Participante criado com sucesso!", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao criar participante: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarParticipantes() {
        try {
            List<Usuario> participantes = usuarioService.buscarTodosOrdenados();
            return new ResponseEntity<>(participantes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
