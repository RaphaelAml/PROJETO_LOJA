package com.meuprojeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void exclusaoTotalVendaBanco(Long idVenda) {


        String value = value =
                          "begin;"
                        + "update nota_fiscal_venda set venda_compra_loja_virt_id = null where venda_compra_loja_virt_id = "+idVenda+";"
                        + "delete from nota_fiscal_venda where venda_compra_loja_virt_id = "+idVenda+";"
                        + "delete from item_venda_loja where venda_compra_loja_virt_id = "+idVenda+";"
                        + "delete from status_rastreio where venda_compra_loja_virt_id = "+idVenda+";"
                        + "delete from vd_cp_loja_virt where id = "+idVenda+";"
                        + "commit;";

        jdbcTemplate.execute(value);

    }

}
