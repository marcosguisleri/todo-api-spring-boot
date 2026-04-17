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

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@Valid @RequestBody TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.titulo());
        tarefa.setDescricao(dto.descricao());
        tarefa.setDataEntrega(dto.dataEntrega());
        tarefa.setPrioridade(dto.prioridade());
        tarefa.setConcluida(dto.concluida());
        return ResponseEntity.status(201).body(service.salvar(tarefa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(service.atualizar(id, tarefa));
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable Long id) {
        service.deletar(id);
    }

}
