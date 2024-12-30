package com.meuprojeto.repository;


import com.meuprojeto.model.PessoaJuridica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PesssoaRepository extends CrudRepository<PessoaJuridica, Long> {


}
