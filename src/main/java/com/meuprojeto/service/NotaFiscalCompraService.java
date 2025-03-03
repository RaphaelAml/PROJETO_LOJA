package com.meuprojeto.service;

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
}
