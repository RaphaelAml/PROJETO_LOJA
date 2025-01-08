package com.meuprojeto.enums;

public enum TipoPessoa {

    JURIDICA("Juridica"),
    jURIDICA_FORNECEDOR("Juridica e fornecedor"),
    Fisica("Fisica");

    private String descricao;

    private TipoPessoa(String descrica) {
        this.descricao = descrica;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
