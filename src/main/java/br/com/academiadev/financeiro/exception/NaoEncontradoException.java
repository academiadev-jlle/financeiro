package br.com.academiadev.financeiro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class NaoEncontradoException extends Exception {

    public NaoEncontradoException(String message) {
        super(message);
    }
}
