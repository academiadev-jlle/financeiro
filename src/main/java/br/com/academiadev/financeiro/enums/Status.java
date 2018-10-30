package br.com.academiadev.financeiro.enums;

public enum Status {

    PAGO("Pago"), PENDENTE("Pendente");

    private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
