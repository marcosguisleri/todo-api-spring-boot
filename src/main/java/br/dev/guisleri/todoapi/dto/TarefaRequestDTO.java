package br.dev.guisleri.todoapi.dto;

import br.dev.guisleri.todoapi.model.Prioridade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TarefaRequestDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao,
        @NotNull
        LocalDate dataEntrega,
        @NotNull
        Prioridade prioridade,
        boolean concluida
) {
}
