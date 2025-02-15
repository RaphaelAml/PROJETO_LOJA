package com.meuprojeto.repository;

import com.meuprojeto.model.VendaCompraLojaVirtual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface Vd_Cp_loja_Virt_Repository extends JpaRepository<VendaCompraLojaVirtual, Long> {


}
