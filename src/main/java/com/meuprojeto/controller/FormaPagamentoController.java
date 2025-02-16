package com.meuprojeto.controller;

import javax.validation.Valid;


import com.meuprojeto.model.FormaPagamento;
import com.meuprojeto.projetoloja.ExcecaoMsgErro;
import com.meuprojeto.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FormaPagamentoController {


    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;


    @ResponseBody
    @PostMapping(value = "**/salvarFormaPagamento")
    public ResponseEntity<FormaPagamento> salvarFormaPagamento(@RequestBody @Valid FormaPagamento formaPagamento)
            throws ExcecaoMsgErro {

        formaPagamento = formaPagamentoRepository.save(formaPagamento);

        return new ResponseEntity<FormaPagamento>(formaPagamento, HttpStatus.OK);
    }


}
