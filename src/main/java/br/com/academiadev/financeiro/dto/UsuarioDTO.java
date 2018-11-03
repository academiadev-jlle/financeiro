package br.com.academiadev.financeiro.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String created_at;
    private String updatedAt;

}
