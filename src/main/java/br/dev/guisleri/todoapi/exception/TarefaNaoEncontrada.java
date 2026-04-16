package br.dev.guisleri.todoapi.exception;

public class TarefaNaoEncontrada extends RuntimeException {
    public TarefaNaoEncontrada(String message) {
        super(message);
    }
}
