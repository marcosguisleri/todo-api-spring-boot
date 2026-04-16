package br.dev.guisleri.todoapi.controller;

import br.dev.guisleri.todoapi.dto.ErrorDTO;
import br.dev.guisleri.todoapi.exception.TarefaNaoEncontrada;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TarefaNaoEncontrada.class)
    public ResponseEntity<ErrorDTO> handleNotFound(TarefaNaoEncontrada ex) {
        return ResponseEntity
                .status(404)
                .body(new ErrorDTO(ex.getMessage()));
    }

}
