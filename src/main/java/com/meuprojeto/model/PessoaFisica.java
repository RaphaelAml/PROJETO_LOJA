package com.meuprojeto.model;



import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica extends Pessoa {

    private static final long serialVersionUID = 1L;

    @CPF(message = "O CPF informado está invalido")
    @Column(nullable = false)
    private String cpf;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


}
