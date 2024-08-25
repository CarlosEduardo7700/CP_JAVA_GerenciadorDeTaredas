package br.com.fiap.JavaCP1GerenciadorDeTarefas.dtos.tarefa;

import br.com.fiap.JavaCP1GerenciadorDeTarefas.models.enums.StatusDasTarefas;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AtualizarTarefaDto(
        @Size(max = 150)
        String titulo,
        @Size(max = 500)
        String descricao,
        LocalDate dataDeConclusao,
        StatusDasTarefas status,
        String emailDoResponsavel
) {
}
