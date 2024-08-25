package br.com.fiap.JavaCP1GerenciadorDeTarefas.dtos.usuario;

import br.com.fiap.JavaCP1GerenciadorDeTarefas.models.Usuario;

public record DetalhesUsuarioDto(
        Long id,
        String nome,
        String email,
        String senha
) {
    public DetalhesUsuarioDto(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha()
        );
    }
}
