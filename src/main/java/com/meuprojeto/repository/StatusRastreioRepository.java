package com.meuprojeto.repository;

import com.meuprojeto.model.StatusRastreio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StatusRastreioRepository extends JpaRepository<StatusRastreio, Long> {

    @Query(value = "select s from StatusRastreio  s where s.vendaCompraLojaVirtual.id = ?1 order by s.id")
    public List<StatusRastreio> listaRastreioVenda(Long id);



    @Modifying(flushAutomatically = true)
    @Query(nativeQuery = true, value = "update vd_cp_loja_virt set url_rastreio = ?1 where id = ?2")
    public void salvaUrlRastreio(String urlRastreio, Long idVenda);


}
