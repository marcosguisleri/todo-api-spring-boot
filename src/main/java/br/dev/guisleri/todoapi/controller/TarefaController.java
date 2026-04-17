package br.dev.guisleri.todoapi.controller;

import br.dev.guisleri.todoapi.dto.TarefaRequestDTO;
import br.dev.guisleri.todoapi.dto.TarefaResponseDTO;
import br.dev.guisleri.todoapi.model.Tarefa;
import br.dev.guisleri.todoapi.service.ITarefaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final ITarefaService service;

    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> listarTarefas() {

        List<TarefaResponseDTO> lista = service.listarTodas()
                .stream()
                .map(this::toDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarTarefaporId(@PathVariable Long id) {
        Tarefa tarefa = service.buscarPorId(id);
        return ResponseEntity.ok(toDTO(tarefa));
    }

    @PostMapping
    public ResponseEntity<TarefaResponseDTO> criarTarefa(@Valid @RequestBody TarefaRequestDTO dto) {
        Tarefa tarefa = toEntity(dto);
        return ResponseEntity.status(201).body(toDTO(service.salvar(tarefa)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody TarefaRequestDTO dto) {
        Tarefa tarefa = toEntity(dto);
        return ResponseEntity.ok(toDTO(service.atualizar(id, tarefa)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Converter Entidade em DTO
    private TarefaResponseDTO toDTO(Tarefa tarefa) {
        return new TarefaResponseDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getDataEntrega(),
                tarefa.getPrioridade(),
                tarefa.isConcluida()
        );
    }

    // Converte DTO em Tarefa
    private Tarefa toEntity(TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.titulo());
        tarefa.setDescricao(dto.descricao());
        tarefa.setDataEntrega(dto.dataEntrega());
        tarefa.setPrioridade(dto.prioridade());
        tarefa.setConcluida(dto.concluida());

        return tarefa;
    }

}
