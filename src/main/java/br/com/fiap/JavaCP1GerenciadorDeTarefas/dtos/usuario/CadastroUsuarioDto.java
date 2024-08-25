package br.com.fiap.JavaCP1GerenciadorDeTarefas.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroUsuarioDto(
        @NotBlank @Size(max = 200)
        String nome,
        @NotBlank @Email @Size(max = 200)
        String email,
        @NotBlank
        String senha
) {
}
