package com.meuprojeto.controller;

import com.meuprojeto.model.StatusRastreio;
import com.meuprojeto.repository.StatusRastreioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatusRastreioController {

    @Autowired
    private StatusRastreioRepository statusRastreioRepository;

    @ResponseBody
    @GetMapping(value = "**/listaRastreio/{idVenda}")
    public ResponseEntity<List<StatusRastreio>> listaRastreioVenda (@PathVariable ("idVenda") Long idVenda){

        List<StatusRastreio> statusRastreios = statusRastreioRepository.listaRastreioVenda(idVenda);
        return new ResponseEntity<List<StatusRastreio>>(statusRastreios, HttpStatus.OK);
    }

}
