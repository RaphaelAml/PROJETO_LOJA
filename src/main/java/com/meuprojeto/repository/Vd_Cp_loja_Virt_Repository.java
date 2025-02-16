package com.meuprojeto.repository;

import com.meuprojeto.model.VendaCompraLojaVirtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface Vd_Cp_loja_Virt_Repository extends JpaRepository<VendaCompraLojaVirtual, Long> {

    @Query(value = "select a from VendaCompraLojaVirtual a where a.id = ?1 and a.excluido = false ")
    VendaCompraLojaVirtual findByIdExclusao(Long id);

    @Query(value="select i.vendaCompraLojaVirtual from ItemVendaLoja i where "
            + " i.vendaCompraLojaVirtual.excluido = false and i.produto.id = ?1")
    List<VendaCompraLojaVirtual> vendaPorProduto(Long idProduto);

}
