package com.meuprojeto.repository;

import com.meuprojeto.model.NotaFiscalVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaFiscalVendaRepository extends JpaRepository<NotaFiscalVenda, Long> {

    @Query("select  n from NotaFiscalVenda n where n.vendaCompraLojaVirtual.id = ?1")
    List<NotaFiscalVenda> buscarNotaPorVenda(Long idVenda);

    @Query("select  n from NotaFiscalVenda n where n.vendaCompraLojaVirtual.id = ?1")
    NotaFiscalVenda buscarNotaPorVendaUnica(Long idVenda);

}
