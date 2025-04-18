package com.meuprojeto.repository;


import com.meuprojeto.model.BoletoJuno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletoJunoRepository extends JpaRepository<BoletoJuno, Long> {


}
