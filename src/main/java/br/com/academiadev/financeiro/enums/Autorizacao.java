package br.com.academiadev.financeiro.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Autorizacao implements GrantedAuthority {

    ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER");

    private String name;

    Autorizacao(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}