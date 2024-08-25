package br.com.fiap.JavaCP1GerenciadorDeTarefas.controllers;

import br.com.fiap.JavaCP1GerenciadorDeTarefas.dtos.tarefa.AtualizarTarefaDto;
import br.com.fiap.JavaCP1GerenciadorDeTarefas.dtos.tarefa.CriarTarefaDto;
import br.com.fiap.JavaCP1GerenciadorDeTarefas.dtos.tarefa.DetalhesTarefaDto;
import br.com.fiap.JavaCP1GerenciadorDeTarefas.dtos.tarefa.StatusDasTarefasDto;
import br.com.fiap.JavaCP1GerenciadorDeTarefas.models.Tarefa;
import br.com.fiap.JavaCP1GerenciadorDeTarefas.models.enums.StatusDasTarefas;
import br.com.fiap.JavaCP1GerenciadorDeTarefas.repositories.TarefaRepository;
import br.com.fiap.JavaCP1GerenciadorDeTarefas.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("tasks")
public class TarefaController {

    @Autowired
    private TarefaRepository repository;

    @Autowired
    private TokenService tokenService;

    @GetMapping("public/status")
    public ResponseEntity<List<StatusDasTarefasDto>> mostrarStatus() {
        var listaDeStatus = Arrays.stream(StatusDasTarefas.values()).map(StatusDasTarefasDto::new).toList();
        return ResponseEntity.ok(listaDeStatus);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesTarefaDto> criarTarefa(
            @RequestBody @Valid CriarTarefaDto dto,
            UriComponentsBuilder builder,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        var tokenJwt = authorizationHeader.replace("Bearer ", "");
        var emailDoResponsavel = tokenService.getSubject(tokenJwt);
        Tarefa tarefa = new Tarefa(dto.titulo(), dto.descricao(), dto.dataDeConclusao(), StatusDasTarefas.PENDENTE, emailDoResponsavel);
        repository.save(tarefa);
        var uri = builder.path("/tarefas/{id}").buildAndExpand(tarefa.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesTarefaDto(tarefa));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesTarefaDto>> mostrarTarefas(Pageable pageable){

        var listaDeTarefas = repository.findAll(pageable).map(DetalhesTarefaDto::new);

        return ResponseEntity.ok(listaDeTarefas);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesTarefaDto> atualizarTarefa(
            @PathVariable("id") Long id,
            @RequestBody @Valid AtualizarTarefaDto dto,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        var tokenJwt = authorizationHeader.replace("Bearer ", "");
        var emailDoResponsavel = tokenService.getSubject(tokenJwt);

        var tarefa = repository.getReferenceById(id);

        if (tarefa.getEmailDoResponsavel().equals(emailDoResponsavel)) {
            tarefa.atualizarDados(dto);
            return ResponseEntity.ok(new DetalhesTarefaDto(tarefa));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        var tokenJwt = authorizationHeader.replace("Bearer ", "");
        var emailDoResponsavel = tokenService.getSubject(tokenJwt);

        var tarefa = repository.getReferenceById(id);

        if (tarefa.getEmailDoResponsavel().equals(emailDoResponsavel)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
