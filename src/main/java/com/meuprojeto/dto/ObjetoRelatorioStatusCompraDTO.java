package com.meuprojeto.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ObjetoRelatorioStatusCompraDTO implements Serializable {


    private static final long serialVersionUID = 1L;


    @NotEmpty(message = "Informa a data inicial")
    private String dataInicial;


    @NotEmpty(message = "Informa a data final")
    private String dataFinal;


    private String codigoProduto ="";
    private String nomeProduto ="";
    private String emailCliente ="";
    private String telefoneCliente ="";
    private String valorVendaProduto ="";
    private String codigoCliente ="";
    private String nomeCliente ="";
    private String quantidadeEstoque ="";
    private String codigoVenda ="";
    private String statusVenda ="";

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getValorVendaProduto() {
        return valorVendaProduto;
    }

    public void setValorVendaProduto(String valorVendaProduto) {
        this.valorVendaProduto = valorVendaProduto;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(String quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(String codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public String getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(String statusVenda) {
        this.statusVenda = statusVenda;
    }
}
