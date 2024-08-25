package br.com.fiap.JavaCP1GerenciadorDeTarefas.controllers;

import br.com.fiap.JavaCP1GerenciadorDeTarefas.dtos.usuario.CadastroUsuarioDto;
import br.com.fiap.JavaCP1GerenciadorDeTarefas.dtos.usuario.DadosAutenticacaDto;
import br.com.fiap.JavaCP1GerenciadorDeTarefas.dtos.usuario.DetalhesUsuarioDto;
import br.com.fiap.JavaCP1GerenciadorDeTarefas.dtos.usuario.TokenJwtDto;
import br.com.fiap.JavaCP1GerenciadorDeTarefas.models.Usuario;
import br.com.fiap.JavaCP1GerenciadorDeTarefas.repositories.UsuarioRepository;
import br.com.fiap.JavaCP1GerenciadorDeTarefas.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("register")
    @Transactional
    public ResponseEntity<DetalhesUsuarioDto> cadastrarUsuario(@RequestBody @Valid CadastroUsuarioDto dto, UriComponentsBuilder builder){
        Usuario usuario = new Usuario(dto.nome(), dto.email(), passwordEncoder.encode(dto.senha()));
        repository.save(usuario);
        var uri = builder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesUsuarioDto(usuario));
    }

    @PostMapping("login")
    public ResponseEntity<TokenJwtDto> login(@RequestBody DadosAutenticacaDto dto){
        var token = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = manager.authenticate(token);
        var tokenJwt = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJwtDto(tokenJwt));
    }
}
