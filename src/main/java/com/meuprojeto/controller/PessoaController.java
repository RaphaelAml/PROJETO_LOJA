package com.meuprojeto.controller;

import com.meuprojeto.model.PessoaJuridica;
import com.meuprojeto.projetoloja.ExcecaoMsgErro;
import com.meuprojeto.repository.PesssoaRepository;
import com.meuprojeto.service.PessoaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaController {

    @Autowired
    private PesssoaRepository pesssoaRepository;

    @Autowired
    private PessoaUserService pessoaUserService;

    /*end-point é microsservicos é um API*/
    @ResponseBody
    @PostMapping(value = "**/salvarPj")
    public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody PessoaJuridica pessoaJuridica) throws ExcecaoMsgErro {

        if (pessoaJuridica == null) {
            throw new ExcecaoMsgErro("Pessoa juridica nao pode ser NULL");
        }

        if (pessoaJuridica.getId() == null && pesssoaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj()) != null) {
            throw new ExcecaoMsgErro("Já existe CNPJ cadastrado com o número: " + pessoaJuridica.getCnpj());
        }

        pessoaJuridica = pessoaUserService.salvarPessoaJuridica(pessoaJuridica);

        return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
    }

}


