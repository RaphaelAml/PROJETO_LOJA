package com.meuprojeto.service;

import com.meuprojeto.dto.ItemVendaDTO;
import com.meuprojeto.dto.VendaCompraLojaVirtualDTO;
import com.meuprojeto.model.ItemVendaLoja;
import com.meuprojeto.model.VendaCompraLojaVirtual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void exclusaoTotalVendaBanco2(Long idVenda) {
        String sql = "begin; update vd_cp_loja_virt set excluido = true where id = " + idVenda +"; commit;";
        jdbcTemplate.update(sql);
    }

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

    public void ativaRegistroVendaBanco(Long idVenda) {

        String sql = "begin; update vd_cp_loja_virt set excluido = false where id = " + idVenda +"; commit;";
        jdbcTemplate.update(sql);

    }


    public VendaCompraLojaVirtualDTO consultaVenda(VendaCompraLojaVirtual compraLojaVirtual) {


        VendaCompraLojaVirtualDTO compraLojaVirtualDTO = new VendaCompraLojaVirtualDTO();

        compraLojaVirtualDTO.setValorTotal(compraLojaVirtual.getValorTotal());
        compraLojaVirtualDTO.setPessoa(compraLojaVirtual.getPessoa());

        compraLojaVirtualDTO.setEntrega(compraLojaVirtual.getEnderecoEntrega());
        compraLojaVirtualDTO.setCobranca(compraLojaVirtual.getEnderecoCobranca());

        compraLojaVirtualDTO.setValorDesconto(compraLojaVirtual.getValorDesconto());
        compraLojaVirtualDTO.setValorFrete(compraLojaVirtual.getValorFrete());
        compraLojaVirtualDTO.setId(compraLojaVirtual.getId());

        for (ItemVendaLoja item : compraLojaVirtual.getItemVendaLojas()) {

            ItemVendaDTO itemVendaDTO = new ItemVendaDTO();
            itemVendaDTO.setQuantidade(item.getQuantidade());
            itemVendaDTO.setProduto(item.getProduto());

            compraLojaVirtualDTO.getItemVendaLoja().add(itemVendaDTO);
        }

        return compraLojaVirtualDTO;
    }


}
