package com.meuprojeto.service;

import com.meuprojeto.dto.ObjetoRelatorioStatusCompraDTO;
import com.meuprojeto.dto.ObjetoRequisicaoRelatorioProdutoAlertaEstoque;
import com.meuprojeto.dto.ObjetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotaFiscalCompraService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * Este relatorio mostra status de compra do cliente
     * @param objetoRelatorioStatusCompraDTO
     * @return List<ObjetoRelatorioStatusCompra>
     */

    public List<ObjetoRelatorioStatusCompraDTO> relatorioStatusVendaLojaVirtual(ObjetoRelatorioStatusCompraDTO objetoRelatorioStatusCompraDTO){

        List<ObjetoRelatorioStatusCompraDTO> retorno = new ArrayList<ObjetoRelatorioStatusCompraDTO>();

        String sql = " select p.id as codigoProduto, " +
                " p.nome as nomeProduto, " +
                " p.valor_venda as valorVendaProduto,  " +
                " pf.id as codigoCliente, " +
                " pf.nome as nomeCliente, " +
                " pf.email as emailCliente, " +
                " pf.telefone as telefoneCliente," +
                " p.qtd_estoque as quantidadeEstoque, " +
                " vclv.id as codigoVenda, " +
                " vclv.status_venda_loja_virtual as statusVenda " +
                " from vd_cp_loja_virt as vclv " +
                " inner join item_venda_loja as ivl on ivl.venda_compra_loja_virt_id = vclv.id " +
                " inner join produto as p on p.id = ivl.produto_id " +
                " inner join pessoa_fisica as pf on  pf.id = vclv.pessoa_id ";


        sql+= " where vclv.data_venda >= '"+ objetoRelatorioStatusCompraDTO.getDataInicial()+"' and vclv.data_venda  <= '"+ objetoRelatorioStatusCompraDTO.getDataFinal()+"' ";

        if(!objetoRelatorioStatusCompraDTO.getNomeProduto().isEmpty()) {
            sql += " and upper(p.nome) like upper('%"+ objetoRelatorioStatusCompraDTO.getNomeProduto()+"%') ";
        }

        if (!objetoRelatorioStatusCompraDTO.getStatusVenda().isEmpty()) {
            sql+= " and vclv.status_venda_loja_virtual in ('"+ objetoRelatorioStatusCompraDTO.getStatusVenda()+"') ";
        }

        if (!objetoRelatorioStatusCompraDTO.getNomeCliente().isEmpty()) {
            sql += " and pf.nome like '%"+ objetoRelatorioStatusCompraDTO.getNomeCliente()+"%' ";
        }


        retorno = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ObjetoRelatorioStatusCompraDTO.class));

        return retorno;

    }


    /**
     * Relatorio de compras de produtos para loja
     * Este relatorio permite saber os produtos comprados para serem vendidos pelo loja vitual
     * 
     * todos os produtos tem relação com a nota fiscal de compra/venda
     * @param objetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO
     * @return  @param dataInicio e dataFinal são parametros obrigatorios
     * @return List<ObjetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO>
     */

    public List<ObjetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO>
    gerarRelatorioProdutoCompraNota(ObjetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO
                                            objetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO) {

        List<ObjetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO> retorno = new
                ArrayList<ObjetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO>();

        String sql = " select p.id as codigoProduto, p.nome as nomeProduto, p.valor_venda as valorVendaProduto, nip.quantidade as quantidadeComprada, " +
                " pj.id as codigoFornecedor, pj.nome as nomeFornecedor, nfc.data_compra as dataCompra " +
                " from nota_fiscal_compra as nfc " +
                " inner join nota_item_produto as nip on nfc.id = nota_fiscal_compra_id " +
                " inner join produto as p on p.id = nip.produto_id " +
                " inner join pessoa_juridica as pj on pj.id = nfc.pessoa_id where ";

        sql += " nfc.data_compra >='"+objetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO.getDataInicial()+"' and ";
        sql += " nfc.data_compra <='"+objetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO.getDataFinal()+"' ";

        if(!objetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO.getCodigoNota().isEmpty()){
            sql += " and nfc.id = " + objetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO.getCodigoNota() + " ";
        }

        if(!objetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO.getCodigoProduto().isEmpty()){
            sql += " and p.id = " + objetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO.getCodigoProduto() + " ";
        }

        if (!objetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO.getNomeProduto().isEmpty()) {
            sql += " and upper(p.nome) LIKE upper('%" + objetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO.getNomeProduto() + "%') ";
        }


        if (!objetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO.getNomeFornecedor().isEmpty()) {
            sql += " and upper(pj.nome) LIKE upper('%" + objetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO.getNomeFornecedor() + "%') ";
        }

        retorno = jdbcTemplate.query(sql, new BeanPropertyRowMapper(
                ObjetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO.class));

        return retorno;
    }


    /**
     * Este relatorio retorna os produtos que estao com estoque menor ou igual a quantidade definida no campo de qtde_alerta_estoque
     * @param alertaEstoque
     * @return List<ObjetoRequisicaoRelatorioProdutoAlertaEstoque> Lista de objetos
     */

    public List<ObjetoRequisicaoRelatorioProdutoAlertaEstoque> gerarRelatorioAlertaEstoque(ObjetoRequisicaoRelatorioProdutoAlertaEstoque alertaEstoque){

        List<ObjetoRequisicaoRelatorioProdutoAlertaEstoque> retorno = new ArrayList<ObjetoRequisicaoRelatorioProdutoAlertaEstoque>();

        String sql = " select p.id as codigoProduto, p.nome as nomeProduto, p.valor_venda as valorVendaProduto, nip.quantidade as quantidadeComprada, " +
                " pj.id as codigoFornecedor, pj.nome as nomeFornecedor, nfc.data_compra as dataCompra, " +
                " p.qtd_estoque as quantidadeEstoque, " +
                " p.qtde_alerta_estoque as quantidadeAlertaEstoque " +
                " from nota_fiscal_compra as nfc " +
                " inner join nota_item_produto as nip on nfc.id = nota_fiscal_compra_id " +
                " inner join produto as p on p.id = nip.produto_id " +
                " inner join pessoa_juridica as pj on pj.id = nfc.pessoa_id where ";

        sql += " nfc.data_compra >='"+alertaEstoque.getDataInicial()+"' and ";
        sql += " nfc.data_compra <='"+alertaEstoque.getDataFinal()+"' ";
        sql += " and p.alerta_qtde_estoque = true and p.qtd_estoque <= p.qtde_alerta_estoque ";

        if(!alertaEstoque.getCodigoNota().isEmpty()){
            sql += " and nfc.id = " + alertaEstoque.getCodigoNota() + " ";
        }

        if(!alertaEstoque.getCodigoProduto().isEmpty()){
            sql += " and p.id = " + alertaEstoque.getCodigoProduto() + " ";
        }

        if (!alertaEstoque.getNomeProduto().isEmpty()) {
            sql += " and upper(p.nome) LIKE upper('%" + alertaEstoque.getNomeProduto() + "%') ";
        }


        if (!alertaEstoque.getNomeFornecedor().isEmpty()) {
            sql += " and upper(pj.nome) LIKE upper('%" + alertaEstoque.getNomeFornecedor() + "%') ";
        }

        retorno = jdbcTemplate.query(sql, new BeanPropertyRowMapper(
                ObjetoRequisicaoRelatorioProdutoAlertaEstoque.class));

        return retorno;

    }
}
