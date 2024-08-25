package br.com.fiap.JavaCP1GerenciadorDeTarefas.models;

import br.com.fiap.JavaCP1GerenciadorDeTarefas.dtos.tarefa.AtualizarTarefaDto;
import br.com.fiap.JavaCP1GerenciadorDeTarefas.models.enums.StatusDasTarefas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "JACP4_TAREFA")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue
    @Column(name = "id_tarefa")
    private Long id;

    @Column(name = "ds_titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "ds_tarafa", length = 500, nullable = false)
    private String descricao;

    @Column(name = "dt_conclusao", nullable = false)
    private LocalDate dataDeConclusao;

    @Enumerated(EnumType.STRING)
    @Column(name = "st_tarefa", length = 25, nullable = false)
    private StatusDasTarefas status;

    @Column(name = "ds_email_responsavel", length = 200, nullable = false)
    private String emailDoResponsavel;

    public Tarefa(String titulo, String descricao, LocalDate dataDeConclusao, StatusDasTarefas status, String emailDoResponsavel) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataDeConclusao = dataDeConclusao;
        this.status = status;
        this.emailDoResponsavel = emailDoResponsavel;
    }

    public void atualizarDados(AtualizarTarefaDto dto) {
        if (dto.titulo() != null)
            this.titulo = dto.titulo();
        if (dto.descricao() != null)
            this.descricao = dto.descricao();
        if (dto.dataDeConclusao() != null)
            this.dataDeConclusao = dto.dataDeConclusao();
        if (dto.status() != null)
            this.status = dto.status();
    }
}
