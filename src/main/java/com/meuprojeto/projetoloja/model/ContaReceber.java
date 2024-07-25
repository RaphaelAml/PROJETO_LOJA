package com.meuprojeto.projetoloja.model;

import com.meuprojeto.projetoloja.enums.StatusContaReceber;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "conta_receber")
@SequenceGenerator(name = "seq_conta_receber", sequenceName = "seq_conta_receber", allocationSize = 1, initialValue = 1)
public class ContaReceber implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_receber")
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusContaReceber status;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dt_Vencimento;

    @Temporal(TemporalType.DATE)
    private Date dt_Pagamento;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    private BigDecimal valorDesconto;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusContaReceber getStatus() {
        return status;
    }

    public void setStatus(StatusContaReceber status) {
        this.status = status;
    }

    public Date getDt_Vencimento() {
        return dt_Vencimento;
    }

    public void setDt_Vencimento(Date dt_Vencimento) {
        this.dt_Vencimento = dt_Vencimento;
    }

    public Date getDt_Pagamento() {
        return dt_Pagamento;
    }

    public void setDt_Pagamento(Date dt_Pagamento) {
        this.dt_Pagamento = dt_Pagamento;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContaReceber that)) return false;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
