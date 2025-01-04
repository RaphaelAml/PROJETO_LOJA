package com.meuprojeto.repository;


import com.meuprojeto.model.PessoaFisica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long> {




}
