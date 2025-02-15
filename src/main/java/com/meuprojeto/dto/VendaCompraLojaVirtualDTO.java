package com.meuprojeto.dto;

import com.meuprojeto.model.Endereco;
import com.meuprojeto.model.ItemVendaLoja;
import com.meuprojeto.model.Pessoa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendaCompraLojaVirtualDTO {

    private Long id;

    private BigDecimal valorTotal;

    private BigDecimal valorDesconto;

    private Pessoa pessoa;

    private Endereco cobranca;

    private Endereco entrega;

    private BigDecimal valorFrete;

    private List<ItemVendaDTO> itemVendaLoja = new ArrayList<ItemVendaDTO>();

    public void setItemVendaLoja(List<ItemVendaDTO> itemVendaLoja) {
        this.itemVendaLoja = itemVendaLoja;
    }

    public List<ItemVendaDTO> getItemVendaLoja() {
        return itemVendaLoja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Endereco getCobranca() {
        return cobranca;
    }

    public void setCobranca(Endereco cobranca) {
        this.cobranca = cobranca;
    }

    public Endereco getEntrega() {
        return entrega;
    }

    public void setEntrega(Endereco entrega) {
        this.entrega = entrega;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
