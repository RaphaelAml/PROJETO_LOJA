package com.meuprojeto.controller;

import com.meuprojeto.model.PessoaFisica;
import com.meuprojeto.model.PessoaJuridica;
import com.meuprojeto.projetoloja.ExcecaoMsgErro;
import com.meuprojeto.repository.PessoaRepository;
import com.meuprojeto.service.PessoaUserService;
import com.meuprojeto.util.ValidaCNPJ;
import com.meuprojeto.util.ValidaCPF;
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
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaUserService pessoaUserService;

    /*end-point é microsservicos é um API*/
    @ResponseBody
    @PostMapping(value = "**/salvarPj")
    public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody PessoaJuridica pessoaJuridica) throws ExcecaoMsgErro {

        if (pessoaJuridica == null) {
            throw new ExcecaoMsgErro("Pessoa juridica nao pode ser NULL");
        }

        if (pessoaJuridica.getId() == null && pessoaRepository.existeCnpjCadastrado(pessoaJuridica.getCnpj()) != null) {
            throw new ExcecaoMsgErro("Já existe CNPJ cadastrado com o número: " + pessoaJuridica.getCnpj());
        }

        if (pessoaJuridica.getId() == null && pessoaRepository.existeInsEstadualCadastrado(pessoaJuridica.getInscEstadual()) != null) {
            throw new ExcecaoMsgErro("Já existe Inscrição Estadual cadastrado com o número: " + pessoaJuridica.getInscEstadual());
        }

        if (!ValidaCNPJ.isCNPJ(pessoaJuridica.getCnpj())){
            throw new ExcecaoMsgErro("Cnpj : " + pessoaJuridica.getCnpj() + " está invalido");
        }

        pessoaJuridica = pessoaUserService.salvarPessoaJuridica(pessoaJuridica);

        return new ResponseEntity<PessoaJuridica>(pessoaJuridica, HttpStatus.OK);
    }

    /*end-point é microsservicos é um API*/
    @ResponseBody
    @PostMapping(value = "**/salvarPf")
    public ResponseEntity<PessoaFisica> salvarPf(@RequestBody PessoaFisica pessoaFisica) throws ExcecaoMsgErro {

        if (pessoaFisica == null) {
            throw new ExcecaoMsgErro("Pessoa fisica não pode ser NULL");
        }

        if (pessoaFisica.getId() == null && pessoaRepository.existeCpfCadastrado(pessoaFisica.getCpf()) != null) {
            throw new ExcecaoMsgErro("Já existe CPF cadastrado com o número: " + pessoaFisica.getCpf());
        }


        if (!ValidaCPF.isCPF(pessoaFisica.getCpf())){
            throw new ExcecaoMsgErro("CPF : " + pessoaFisica.getCpf() + " está invalido");
        }

        pessoaFisica = pessoaUserService.salvarPessoaFisica(pessoaFisica);

        return new ResponseEntity<PessoaFisica>(pessoaFisica, HttpStatus.OK);
    }

}


