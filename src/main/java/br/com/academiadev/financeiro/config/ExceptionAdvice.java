package br.com.academiadev.financeiro.config;

import br.com.academiadev.financeiro.dto.ValidationErrorDTO;
import br.com.academiadev.financeiro.exception.NaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleNaoEncontradoException(NaoEncontradoException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ValidationErrorDTO handleAllErros(Throwable t) {
        return ValidationErrorDTO.builder()
                .message("Ocorreu um erro motherfucker")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(System.currentTimeMillis())
                .build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(p -> p.getField(), FieldError::getDefaultMessage));

        return ValidationErrorDTO.builder().message("Não foi possível processar esta requisição.")
                .status(UNPROCESSABLE_ENTITY.value())
                .timestamp(System.currentTimeMillis())
                .erros(fieldErrors).build();

    }
}
