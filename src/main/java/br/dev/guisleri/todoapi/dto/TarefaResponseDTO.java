package br.dev.guisleri.todoapi.dto;

import br.dev.guisleri.todoapi.model.Prioridade;

import java.time.LocalDate;

public record TarefaResponseDTO(
        Long id,
        String titulo,
        String descricao,
        LocalDate dataEntrega,
        Prioridade prioridade,
        boolean concluida
) {
}
