package com.meuprojeto.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "avaliacao_produto")
@SequenceGenerator(name = "seq_avaliacao_produto", sequenceName = "seq_avaliacao_produto", allocationSize = 1, initialValue = 1)
public class AvaliacaoProduto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_avaliacao_produto")
    private Long id;

    @Min(value = 1, message = "A nota deve ser pelo menos 1")
    @Max(value = 10, message = "A nota deve ser pelo menos 10")
    @Column(nullable = false)
    private Integer nota;

    @NotEmpty(message = "Informe uma avaliação para o produto")
    @Column(nullable = false)
    private String descricao;

    @ManyToOne(targetEntity = PessoaFisica.class)
    @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private PessoaFisica pessoa;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "produto_fk"))
    private Produto produto;

    @ManyToOne(targetEntity = PessoaJuridica.class)
    @JoinColumn(name = "empresa_id", nullable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
    private PessoaJuridica empresa;

    public PessoaJuridica getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PessoaJuridica empresa) {
        this.empresa = empresa;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public PessoaFisica getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaFisica pessoa) {
        this.pessoa = pessoa;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AvaliacaoProduto that)) return false;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
