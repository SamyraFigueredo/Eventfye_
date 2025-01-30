package com.webservice.eventfye.Controller;

import com.webservice.eventfye.Dto.InscricoesEventoResponse;
import com.webservice.eventfye.Dto.UserDTO;
import com.webservice.eventfye.Model.Evento;
import com.webservice.eventfye.Model.Inscricao;
import com.webservice.eventfye.Model.Usuario;
import com.webservice.eventfye.Service.InscricaoService;
import com.webservice.eventfye.Service.EventoService;
import com.webservice.eventfye.Service.KeycloakService;
import com.webservice.eventfye.Service.UsuarioService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/inscricao")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private KeycloakService keycloakService;

    @PostMapping("/evento/{id_evento}/usuario/{id_usuario}")
    public ResponseEntity<String> inscreverNoEvento(@PathVariable Long id_evento, @PathVariable UUID id_usuario) {
        try {
            Evento evento = eventoService.findById(id_evento);
            if (evento == null) {
                return new ResponseEntity<>("Evento não encontrado.", HttpStatus.NOT_FOUND);
            }
            UserRepresentation usuario = keycloakService.findById(id_usuario);
            if (usuario == null) {
                return new ResponseEntity<>("Usuário não encontrado.", HttpStatus.NOT_FOUND);
            }

            if(inscricaoService.buscarInscricoesPorUsuario(id_usuario).stream().map(Inscricao::getEvento).toList().contains(evento)){
                return new ResponseEntity<>("Voce ja esta cadastrado nesse evento!", HttpStatus.BAD_REQUEST);
            }

            Inscricao inscricao = new Inscricao();
            inscricao.setEvento(evento);
            inscricao.setUsuarioId(id_usuario);
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
    public ResponseEntity<?> listarInscricoesPorUsuario(@PathVariable UUID id_usuario) {
        try {
            return new ResponseEntity<>(inscricaoService.buscarInscricoesPorUsuario(id_usuario), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao listar inscrições do usuário.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/evento/{id_evento}")
    public ResponseEntity<?> listarInscricoesPorEvento(@PathVariable Long id_evento, @AuthenticationPrincipal Jwt principal) {
        try {
            String userId = principal.getClaimAsString("sub");
            if(Objects.equals(eventoService.findById(id_evento).getIdUsuario(), userId)){
                List<InscricoesEventoResponse> inscricoes = inscricaoService.buscarInscricoesPorEvento(id_evento).stream().map(x -> {
                    UserRepresentation user = keycloakService.findById(x.getUsuarioId());
                    return new InscricoesEventoResponse(
                            x.getIdInscricao(),
                            new UserDTO(user.getId(),user.getEmail(),user.getFirstName() + " " + user.getLastName()),
                            x.getStatusInscricao(),
                            x.getDataInscricao()
                    );
                }).toList();
                return new ResponseEntity<>(inscricoes, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Voce nao tem permissao para isso!", HttpStatus.FORBIDDEN);
            }
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

    @PutMapping("cancelar/{id_inscricao}")
    public ResponseEntity<String> cancelarInscricao(@PathVariable Long id_inscricao) {
        try {
            Inscricao inscricao = inscricaoService.atualizarStatusInscricao(id_inscricao, "CANCELADA");
            return new ResponseEntity<>("Inscrição cancelada com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao cancelar inscrição.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
