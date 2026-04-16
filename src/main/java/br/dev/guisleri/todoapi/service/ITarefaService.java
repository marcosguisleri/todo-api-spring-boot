package br.dev.guisleri.todoapi.service;

import br.dev.guisleri.todoapi.model.Tarefa;

import java.util.List;

public interface ITarefaService {
    Tarefa salvar(Tarefa tarefa);
    List<Tarefa> listarTodas();
    Tarefa buscarPorId(Long id);
    Tarefa atualizar(Long id, Tarefa tarefa);
    void deletar(Long id);
}
