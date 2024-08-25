package br.com.fiap.JavaCP1GerenciadorDeTarefas.dtos.tarefa;

import br.com.fiap.JavaCP1GerenciadorDeTarefas.models.enums.StatusDasTarefas;

public record StatusDasTarefasDto(
        StatusDasTarefas status
) {
}
