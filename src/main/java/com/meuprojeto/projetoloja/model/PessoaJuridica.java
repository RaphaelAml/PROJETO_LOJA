package com.meuprojeto.projetoloja.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica extends Pessoa {

    private static final long serialVersionUID = 1L;

    private String cnpj;
    private String inscEstadual;
    private String inscMunicipal;
    private String nomeFantasia;
    private String razaoSocial;
    private String categoria;

    // getters e setters
}
