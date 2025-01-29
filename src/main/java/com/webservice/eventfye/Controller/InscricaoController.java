package com.webservice.eventfye.Controller;

import com.webservice.eventfye.Model.Evento;
import com.webservice.eventfye.Model.Inscricao;
import com.webservice.eventfye.Model.Usuario;
import com.webservice.eventfye.Service.InscricaoService;
import com.webservice.eventfye.Service.EventoService;
import com.webservice.eventfye.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscricao")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/evento/{id_evento}/usuario/{id_usuario}")
    public ResponseEntity<String> inscreverNoEvento(@PathVariable Long id_evento, @PathVariable Long id_usuario) {
        try {
            Evento evento = eventoService.findById(id_evento);
            if (evento == null) {
                return new ResponseEntity<>("Evento não encontrado.", HttpStatus.NOT_FOUND);
            }
            Usuario usuario = usuarioService.buscarUsuarioPorId(id_usuario);
            if (usuario == null) {
                return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
            }

            Inscricao inscricao = new Inscricao();
            inscricao.setEvento(evento);
            inscricao.setUsuario(usuario);
            inscricao.setStatusInscricao(Inscricao.StatusInscricao.PENDENTE.name());
            inscricao = inscricaoService.salvarInscricao(inscricao);

            return new ResponseEntity<>("Inscrição realizada com sucesso!", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao processar a inscrição: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarInscricoes() {
        try {
            return new ResponseEntity<>(inscricaoService.listarTodasInscricoes(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao listar inscrições.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuario/{id_usuario}")
    public ResponseEntity<?> listarInscricoesPorUsuario(@PathVariable Long id_usuario) {
        try {
            return new ResponseEntity<>(inscricaoService.buscarInscricoesPorUsuario(id_usuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao listar inscrições do usuário.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/evento/{id_evento}")
    public ResponseEntity<?> listarInscricoesPorEvento(@PathVariable Long id_evento) {
        try {
            return new ResponseEntity<>(inscricaoService.buscarInscricoesPorEvento(id_evento), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao listar inscrições do evento.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id_inscricao}/status/{novo_status}")
    public ResponseEntity<String> atualizarStatusInscricao(
            @PathVariable Long id_inscricao, @PathVariable String novo_status) {
        try {
            Inscricao inscricao = inscricaoService.atualizarStatusInscricao(id_inscricao, novo_status);
            return new ResponseEntity<>("Status da inscrição atualizado para: " + inscricao.getStatusInscricao(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar status da inscrição.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id_inscricao}")
    public ResponseEntity<String> excluirInscricao(@PathVariable Long id_inscricao) {
        try {
            inscricaoService.excluirInscricao(id_inscricao);
            return new ResponseEntity<>("Inscrição excluída com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao excluir inscrição.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
