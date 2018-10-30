package br.com.academiadev.financeiro.enums;

public enum TipoLancamento {

    RECEBER("Receber"), PAGAR("Pagar");

    private String descricao;

    TipoLancamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
