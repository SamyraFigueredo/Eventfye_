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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public List<Usuario> buscarTodosOrdenados() {
        return usuarioRepository.findAllByOrderByNomeUsuarioAsc();
    }

    public Usuario buscarUsuarioPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o id: " + idUsuario));
    }

    public Optional<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findByNomeUsuario(nome);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmailUsuario(email);
    }

    public Usuario salvarUsuario(Usuario usuario) {
        validarUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario.getNomeUsuario() == null || usuario.getNomeUsuario().isEmpty()) {
            throw new IllegalArgumentException("Nome do participante é obrigatório.");
        }

        if (usuario.getDataNascimento() == null) {
            throw new IllegalArgumentException("Data de nascimento é obrigatória.");
        }

        if (!validarEmail(usuario.getEmailUsuario())) {
            throw new IllegalArgumentException("E-mail inválido.");
        }

        if (usuario.getSenhaUsuario() == null || usuario.getSenhaUsuario().length() < 6) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres.");
        }
    }

    private boolean validarEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
                .senhaUsuario(senha)
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
}
