package br.dev.guisleri.todoapi.repo;

import br.dev.guisleri.todoapi.model.Tarefa;

import org.springframework.data.repository.ListCrudRepository;

public interface TarefaRepo extends ListCrudRepository<Tarefa, Long> {
}
