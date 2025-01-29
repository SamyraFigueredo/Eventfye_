package com.webservice.eventfye.Service;

import com.webservice.eventfye.Model.Usuario;
import com.webservice.eventfye.Repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findByNomeUsuario(nome);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmailUsuario(email);
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public String registrarParticipante(String nome, LocalDate dataNascimento, String email, String senha) {
        if (nome == null || nome.isEmpty()) {
            return "O nome é obrigatório.";
        }
        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
            return "A data de nascimento é inválida.";
        }
        if (email == null || email.isEmpty()) {
            return "O e-mail é obrigatório.";
        }
        if (senha == null || senha.length() < 6) {
            return "A senha deve ter pelo menos 6 caracteres.";
        }

        if (verificarEmailExistente(email)) {
            return "O e-mail já está registrado.";
        }

        Usuario usuario = Usuario.builder()
                .nomeUsuario(nome)
                .dataNascimento(dataNascimento)
                .emailUsuario(email)
                .build();

        usuarioRepository.save(usuario);
        return "Usuário cadastrado com sucesso!";
    }

    public boolean verificarEmailExistente(String email) {
        return usuarioRepository.findByEmailUsuario(email).isPresent();
    }

    public boolean verificarNomeExistente(String nome) {
        return usuarioRepository.findByNomeUsuario(nome).isPresent();
    }

    public Usuario buscarUsuarioPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o id: " + idUsuario));
    }
}
