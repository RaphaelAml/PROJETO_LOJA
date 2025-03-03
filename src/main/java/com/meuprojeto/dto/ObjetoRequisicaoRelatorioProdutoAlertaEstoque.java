package com.meuprojeto.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ObjetoRequisicaoRelatorioProdutoAlertaEstoque implements Serializable {

    private static final long serialVersionUID = 1L;


    private String nomeProduto = "";

    @NotEmpty(message = "Informar a data Inicial")
    private String dataInicial;

    @NotEmpty(message = "Informa a dataFinal")
    private String dataFinal;

    private String codigoNota = "";
    private String codigoProduto = "";
    private String valorVendaProduto = "";
    private String quantidadeComprada = "";
    private String codigoFornecedor = "";
    private String nomeFornecedor = "";
    private String dataCompra = "";
    private String quantidadeEstoque;
    private String quantidadeAlertaEstoque;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

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

    public String getCodigoNota() {
        return codigoNota;
    }

    public void setCodigoNota(String codigoNota) {
        this.codigoNota = codigoNota;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getValorVendaProduto() {
        return valorVendaProduto;
    }

    public void setValorVendaProduto(String valorVendaProduto) {
        this.valorVendaProduto = valorVendaProduto;
    }

    public String getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(String quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    public String getCodigoFornecedor() {
        return codigoFornecedor;
    }

    public void setCodigoFornecedor(String codigoFornecedor) {
        this.codigoFornecedor = codigoFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(String quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getQuantidadeAlertaEstoque() {
        return quantidadeAlertaEstoque;
    }

    public void setQuantidadeAlertaEstoque(String quantidadeAlertaEstoque) {
        this.quantidadeAlertaEstoque = quantidadeAlertaEstoque;
    }
}
