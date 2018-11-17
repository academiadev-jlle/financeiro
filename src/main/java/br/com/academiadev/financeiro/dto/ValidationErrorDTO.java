package br.com.academiadev.financeiro.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class ValidationErrorDTO {

    private Long timestamp;
    private int status;
    private String message;
    private Map<String, String> erros;

}
