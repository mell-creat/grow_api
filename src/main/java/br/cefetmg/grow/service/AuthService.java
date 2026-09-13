package br.cefetmg.grow.service;

import br.cefetmg.grow.model.Usuario;
import br.cefetmg.grow.repository.UsuarioRepository;
import br.cefetmg.grow.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public String autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (!usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Credenciais inválidas");
        }

        usuario.setUltimoLogin(LocalDateTime.now());
        usuarioRepository.save(usuario);

        return jwtUtil.gerarToken(usuario.getEmail());
    }
}