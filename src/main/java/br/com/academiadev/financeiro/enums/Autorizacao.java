package br.com.academiadev.financeiro.enums;

import org.springframework.security.core.GrantedAuthority;

public class Autorizacao implements GrantedAuthority {

    private String name;

    public Autorizacao(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}