package com.meuprojeto.repository;

import com.meuprojeto.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional //para o repository gerenciar as transactional do banco
public interface AcessoRepository extends JpaRepository<Acesso, Long> {

    @Query("Select a from Acesso a where upper(trim(a.descricao)) like %?1% ")
    List<Acesso> buscarAcessoDesc(String desc);
}
