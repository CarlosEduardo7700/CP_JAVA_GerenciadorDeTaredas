package br.com.fiap.JavaCP1GerenciadorDeTarefas.dtos.tarefa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CriarTarefaDto(
        @NotBlank @Size(max = 150)
        String titulo,
        @NotBlank @Size(max = 500)
        String descricao,
        @NotNull
        LocalDate dataDeConclusao
) {
}
