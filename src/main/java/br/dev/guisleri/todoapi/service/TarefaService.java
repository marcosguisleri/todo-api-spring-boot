package br.dev.guisleri.todoapi.service;

import br.dev.guisleri.todoapi.exception.TarefaNaoEncontrada;
import br.dev.guisleri.todoapi.model.Tarefa;
import br.dev.guisleri.todoapi.repo.TarefaRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService implements ITarefaService {

    private final TarefaRepo tarefaRepo;

    @Override
    public Tarefa salvar(Tarefa tarefa) {
        tarefa.setDataCriacao(LocalDateTime.now());
        return tarefaRepo.save(tarefa);
    }

    @Override
    public List<Tarefa> listarTodas() {
        return tarefaRepo.findAll();
    }

    @Override
    public Tarefa buscarPorId(Long id) {
        return tarefaRepo.findById(id).orElseThrow(() -> new TarefaNaoEncontrada("Tarefa não encontrada - id: " + id));
    }

    @Override
    public Tarefa atualizar(Long id, Tarefa tarefa) {
        Tarefa tarefaExistente = tarefaRepo.findById(id)
                .orElseThrow(() -> new TarefaNaoEncontrada("Tarefa não encontrada - id: " + id));

        tarefaExistente.setTitulo(tarefa.getTitulo());
        tarefaExistente.setDescricao(tarefa.getDescricao());
        tarefaExistente.setDataEntrega(tarefa.getDataEntrega());
        tarefaExistente.setPrioridade(tarefa.getPrioridade());
        tarefaExistente.setConcluida(tarefa.isConcluida());

        return tarefaRepo.save(tarefaExistente);
    }

    @Override
    public void deletar(Long id) {
        tarefaRepo.deleteById(id);
    }
}
