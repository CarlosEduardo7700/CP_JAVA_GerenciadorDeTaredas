package br.com.fiap.JavaCP1GerenciadorDeTarefas.dtos.tarefa;

import br.com.fiap.JavaCP1GerenciadorDeTarefas.models.Tarefa;
import br.com.fiap.JavaCP1GerenciadorDeTarefas.models.enums.StatusDasTarefas;

import java.time.LocalDate;

public record DetalhesTarefaDto(
        Long id,
        String titulo,
        String descricao,
        LocalDate dataDeConclusao,
        StatusDasTarefas status,
        String emailDoResponsavel
) {
    public DetalhesTarefaDto(Tarefa tarefa) {
        this(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getDataDeConclusao(),
                tarefa.getStatus(),
                tarefa.getEmailDoResponsavel()
        );
    }
}
