package br.dev.guisleri.todoapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="tbl_tarefas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo", length = 255, nullable = false)
    private String titulo;

    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    @Column(name = "data_entrega")
    private LocalDate dataEntrega;

    @Column(name = "prioridade")
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Column(name = "concluida")
    private boolean concluida;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
}
