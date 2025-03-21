package com.meuprojeto.repository;


import com.meuprojeto.model.PessoaFisica;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long> {


    @Query(value = "select pf from PessoaFisica pf where upper(trim(pf.nome)) like %?1%")
    public List<PessoaFisica> pesquisaPorNomePF(String nome);


    @Query(value = "select pf from PessoaFisica pf where pf.cpf = ?1")
    public List<PessoaFisica> pesquisaPorCpfPF(String cpf);


}
