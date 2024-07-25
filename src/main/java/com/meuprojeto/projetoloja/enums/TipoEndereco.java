package com.meuprojeto.projetoloja.enums;

public enum TipoEndereco {

    //Valores
    COBRANCA("Cobrança"),
    ENTREGA("Entrega");

    private String descricao;

    TipoEndereco(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
