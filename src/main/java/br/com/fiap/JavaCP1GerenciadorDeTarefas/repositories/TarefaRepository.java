package br.com.fiap.JavaCP1GerenciadorDeTarefas.repositories;

import br.com.fiap.JavaCP1GerenciadorDeTarefas.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
