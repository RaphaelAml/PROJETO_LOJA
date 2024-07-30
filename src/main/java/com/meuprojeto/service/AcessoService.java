package com.meuprojeto.service;

import com.meuprojeto.model.Acesso;
import com.meuprojeto.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcessoService {

    @Autowired
    private AcessoRepository acessoRepository;

    /*Metodo: Qualquer tipo de validacao antes de salvar*/
    public Acesso save(Acesso acesso) {

        return acessoRepository.save(acesso);
    }
}
