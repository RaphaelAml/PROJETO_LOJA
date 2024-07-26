package com.meuprojeto.projetoloja.repository;

import com.meuprojeto.projetoloja.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional //para o repository gerenciar as transactional do banco
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
}
