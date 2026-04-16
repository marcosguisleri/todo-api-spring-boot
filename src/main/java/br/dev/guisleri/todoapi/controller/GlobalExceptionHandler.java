package br.dev.guisleri.todoapi.controller;

import br.dev.guisleri.todoapi.dto.ErrorDTO;
import br.dev.guisleri.todoapi.exception.TarefaNaoEncontrada;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TarefaNaoEncontrada.class)
    public ResponseEntity<ErrorDTO> handleNotFound(TarefaNaoEncontrada ex) {
        return ResponseEntity
                .status(404)
                .body(new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidation(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst() .orElse("Erro de validação");

        return ResponseEntity.badRequest().body(new ErrorDTO(mensagem));
    }

}
